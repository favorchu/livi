package com.livi.demo;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.method.HandlerMethod;

import com.google.common.cache.CacheBuilder;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

/**
 * Primary application config
 * 
 * @author favorchu
 *
 */

@Configuration
public class ServiceConfig {
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(//
				new ConcurrentMapCache(DemoConstant.CACHE_200MS, //
						CacheBuilder.newBuilder().expireAfterWrite(//
								200, //
								TimeUnit.MICROSECONDS)//
								.maximumSize(1000)//
								.build().asMap(),
						false), //
				new ConcurrentMapCache(DemoConstant.CACHE_2SEC, //
						CacheBuilder.newBuilder().expireAfterWrite(//
								2, //
								TimeUnit.SECONDS)//
								.maximumSize(10 * 1000)//
								.build().asMap(),
						false), //
				new ConcurrentMapCache(DemoConstant.CACHE_10SEC, //
						CacheBuilder.newBuilder().expireAfterWrite(//
								10, //
								TimeUnit.SECONDS)//
								.maximumSize(10 * 1000)//
								.build().asMap(),
						false), //
				new ConcurrentMapCache(DemoConstant.CACHE_30SEC, //
						CacheBuilder.newBuilder().expireAfterWrite(//
								30, //
								TimeUnit.SECONDS)//
								.maximumSize(10 * 1000)//
								.build().asMap(),
						false), //
				new ConcurrentMapCache(DemoConstant.CACHE_5MIN, //
						CacheBuilder.newBuilder().expireAfterWrite(//
								5, //
								TimeUnit.MINUTES)//
								.maximumSize(10 * 1000)//
								.build().asMap(),
						false)));
		return cacheManager;
	}

	/**
	 * Help to generate the unique signature of the calling method to determine the
	 * cached return value
	 * 
	 * @return
	 */
	@Bean
	@Primary
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {

			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(method.getDeclaringClass().getSimpleName()).append(".").append(method.getName());
				sb.append("(");
				sb.append(ReflectionToStringBuilder.toString(params, ToStringStyle.SHORT_PREFIX_STYLE));
				sb.append(")");
				String key = sb.toString();

				return key;
			}
		};
	}

	/**
	 * Customize the header with extra token
	 * 
	 * @return
	 */
	@Bean
	public OperationCustomizer globalCustHeader() {
		return new OperationCustomizer() {

			@Override
			public Operation customize(Operation operation, HandlerMethod handlerMethod) {
//				Parameter parameterHeader = new Parameter().in(ParameterIn.HEADER.toString())
//						.schema(new StringSchema().name(DemoConstant.JWT_HEADER_NAME)).required(false)
//						.description("Custom Header To be Pass");
				Parameter parameterHeader = new Parameter().in(ParameterIn.HEADER.toString())
						.name(DemoConstant.JWT_HEADER_NAME).description("JWT provided by login")
						.schema(new StringSchema()).example("xxxx.xxxx").required(false);
				operation.addParametersItem(parameterHeader);
				return operation;
			}
		};

	}

//	@Bean
//	public Docket docket() {
//		return new Docket(DocumentationType.OAS_30).forCodeGeneration(true)
//				.globalRequestParameters(globalParameterList()).select()
//				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
//				.build();
//	}
//
//	private List<RequestParameter> globalParameterList() {
//		return Arrays.asList(new RequestParameterBuilder()//
//				.name(DemoConstant.JWT_HEADER_NAME)//
//				.in(ParameterType.HEADER)
//				.query(q -> q.defaultValue("")
//						.model(modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
//				.required(false).build());
//
//	}
}
