package com.livi.demo.common.model.jpa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.livi.demo.common.model.pojo.TaConfigWithCategory;
import com.livi.demo.common.model.pojo.TaConfigWithRange;
import com.livi.demo.common.model.pojo.TaUser;
import com.livi.demo.common.utils.PasswordUtils;

/**
 * For mock database access
 * 
 * @author favorchu
 *
 */
@Component
public class MockRepository {

	// Data collection
	private static final List<TaConfigWithCategory> taConfigsByCategory = new ArrayList<TaConfigWithCategory>();
	private static final List<TaConfigWithRange> taConfigsByRange = new ArrayList<TaConfigWithRange>();
	private static final List<TaUser> taUsers = new ArrayList<TaUser>();
	static {
		// Configuration
		taConfigsByRange.add(new TaConfigWithRange("EMPLOYEE", 1, 1, 1));
		taConfigsByRange.add(new TaConfigWithRange("EMPLOYEE", 2, 5, 2));
		taConfigsByRange.add(new TaConfigWithRange("EMPLOYEE", 6, 10, 3));
		taConfigsByRange.add(new TaConfigWithRange("EMPLOYEE", 11, 15, 5));
		taConfigsByRange.add(new TaConfigWithRange("EMPLOYEE", 51, 200, 8));
		taConfigsByRange.add(new TaConfigWithRange("EMPLOYEE", 201, null, 13));

		taConfigsByCategory.add(new TaConfigWithCategory("COMPANY_TYPE", "SOLE_PROPRIETORSHIP", 1));
		taConfigsByCategory.add(new TaConfigWithCategory("COMPANY_TYPE", "PARTNERSHIP", 3));
		taConfigsByCategory.add(new TaConfigWithCategory("COMPANY_TYPE", "LIMITED_LIABILITY_COMPANY", 5));
		taConfigsByCategory.add(new TaConfigWithCategory("COMPANY_TYPE", "OTHERS", 0));

		taConfigsByRange.add(new TaConfigWithRange("YEAR_OPERATED", 0, 1, 1));
		taConfigsByRange.add(new TaConfigWithRange("YEAR_OPERATED", 2, 3, 2));
		taConfigsByRange.add(new TaConfigWithRange("YEAR_OPERATED", 4, 6, 3));
		taConfigsByRange.add(new TaConfigWithRange("YEAR_OPERATED", 7, 10, 5));
		taConfigsByRange.add(new TaConfigWithRange("YEAR_OPERATED", 11, null, 13));

		// Users
		taUsers.add(new TaUser("favorchu@gmail.com"//
				, null// TODO, copy the token
				, "SENIOR_USER"//
				, PasswordUtils.hashPassword("password")));
		taUsers.add(new TaUser("yschu711@gmail.com"//
				, null// TODO, copy the token
				, "JUNIOR_USER"//
				, PasswordUtils.hashPassword("password")));

	}

	/**
	 * Get the configuration with category by name
	 * 
	 * @param Configuration name
	 * @return
	 */
	public List<TaConfigWithCategory> getConfigsWithCategoryByName(String configName) {
		return taConfigsByCategory.stream()//
				.filter(c -> StringUtils.equalsIgnoreCase(c.getConfigName(), configName)).toList();
	}

	/**
	 * Get the configuration with range by name
	 * 
	 * @param Configuration name
	 * @return
	 */
	public List<TaConfigWithRange> getConfigsWithRangeByName(String configName) {
		return taConfigsByRange.stream()//
				.filter(c -> StringUtils.equalsIgnoreCase(c.getConfigName(), configName)).toList();
	}

	/**
	 * Get one user by user ID
	 * 
	 * @param userId
	 * @return
	 */
	public TaUser getUserByID(String userId) {
		for (TaUser taUser : taUsers)
			if (StringUtils.equalsIgnoreCase(userId, taUser.getUserId()))
				return taUser;
		return null;
	}

}
