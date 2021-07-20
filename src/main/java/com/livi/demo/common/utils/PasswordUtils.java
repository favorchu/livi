package com.livi.demo.common.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author favorchu
 *
 */
public class PasswordUtils {

	/**
	 * Random number to better protect the password
	 */
	private static final Random RANDOM = new SecureRandom();

	private static final String SPLITOR = ":";
	/**
	 * Salt size in saved database
	 */
	private static final int SALT_LEN = 12;

	/**
	 * Hash the password with salt provided
	 * 
	 * @param password
	 * @return
	 */
	public static String hashPassword(String password) {
		// Prepare the salt
		String salt = RandomStringUtils.randomAlphanumeric(SALT_LEN);

		return hashPassword(password, salt);
	}

	/**
	 * This function is going to one-way encrypt the password with salt added<br />
	 * 
	 * Return value = [Salt]:SHA256([Salt]:[Password])
	 * 
	 * @param salt
	 * @param password
	 * @return
	 */
	public static String hashPassword(String password, String salt) {
		String combined = salt + SPLITOR + password;
		return salt + SPLITOR + DigestUtils.sha256Hex(combined);
	}

	/**
	 * Validate the password against the hashed password , <br/>
	 * if salt is contained inside the hashed password, salt is taken count in ,
	 * otherwise, it is a SHA2 check only
	 * 
	 * @param hashedPassword
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String hashedPassword, String password) {
		if (StringUtils.contains(hashedPassword, SPLITOR)) {
			// Salt is provided
			return StringUtils.equalsIgnoreCase(hashedPassword,
					hashPassword(password, StringUtils.substringBefore(hashedPassword, SPLITOR)));
		}
		// No salt
		return StringUtils.equalsIgnoreCase(hashedPassword, DigestUtils.sha256Hex(password));
	}

}
