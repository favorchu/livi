package com.livi.demo.common.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.livi.demo.DemoConstant;

@Service
public class JwtServivce extends AbsService {

	// TODO move to some where secure
	private static final String KEY = "Key";

	@Autowired
	private CryptoService cryptoService;

	/**
	 * @param jwt
	 * @return
	 */
	public DecodedJWT decodeAuthenJWT(String jwt) {
		Algorithm algorithm = getJWTAlgo();
		// Reusable verifier instance
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(DemoConstant.JWT_HEADER_NAME).build();
		DecodedJWT decodedJWT = verifier.verify(jwt);
		return decodedJWT;
	}

	private Algorithm getJWTAlgo() {
		return Algorithm.HMAC256(KEY);
	}

	public String createSecretJwtWithSubject(String subject, String code, int lifeMinute) {
		Algorithm algorithm = getJWTAlgo();
		String jwt = JWT.create()//
				.withClaim(DemoConstant.JWT_CLAIM_SUBJECT, subject)//
				.withClaim(DemoConstant.JWT_CLAIM_CODE, cryptoService.encrypt(code))//
				.withIssuer(DemoConstant.JWT_HEADER_NAME)//
				.withExpiresAt(DateUtils.addMinutes(new Date(), Math.abs(lifeMinute)))//
				.sign(algorithm);
		return jwt;
	}

	public String createSecretJwt(String code, int lifeMinute) {
		Algorithm algorithm = getJWTAlgo();
		String jwt = JWT.create()//
				.withClaim(DemoConstant.JWT_CLAIM_CODE, cryptoService.encrypt(code))//
				.withIssuer(DemoConstant.JWT_HEADER_NAME)//
				// Token will be expire in 1 hrs
				.withExpiresAt(DateUtils.addMinutes(new Date(), Math.abs(lifeMinute)))//
				.sign(algorithm);
		return jwt;
	}

	/**
	 * Verify the input again the saved token
	 * 
	 * @param jwt
	 * @param subject
	 * @param code
	 * @return
	 */
	public boolean verifySubjectJwt(String jwt, String subject, String code) {
		Algorithm algorithm = getJWTAlgo();
		// Reusable verifier instance
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(DemoConstant.JWT_HEADER_NAME).build();
		DecodedJWT decodedJWT = verifier.verify(jwt);
		String jwtSubject = decodedJWT.getClaim(DemoConstant.JWT_CLAIM_SUBJECT).asString();
		String jwtCode = cryptoService.decrypt(decodedJWT.getClaim(DemoConstant.JWT_CLAIM_CODE).asString());

		return StringUtils.endsWithIgnoreCase(jwtSubject, subject) && StringUtils.equals(jwtCode, code);
	}

	public String decryptSecretJwt(String jwt) {
		Algorithm algorithm = getJWTAlgo();
		// Reusable verifier instance
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(DemoConstant.JWT_HEADER_NAME).build();
		DecodedJWT decodedJWT = verifier.verify(jwt);
		return cryptoService.decrypt(decodedJWT.getClaim(DemoConstant.JWT_CLAIM_CODE).asString());
	}

}
