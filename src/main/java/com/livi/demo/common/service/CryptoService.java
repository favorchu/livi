package com.livi.demo.common.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.livi.demo.exception.BusinessRuntimeExcepion;

@Service
public class CryptoService extends AbsService {
	private static final String AES = "AES";

	// TODO move to some where secure
	private static final String KEY = "Key";

	private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

	/**
	 * Common AES encryption
	 * 
	 * @param encryptedString
	 * @return
	 */
	public String decrypt(final String encryptedString) {
		return decrypt(encryptedString, KEY);
	}

	/**
	 * Encrypt with custome seed
	 * 
	 * @param encryptedString
	 * @param seed
	 * @return
	 */
	public String decrypt(final String encryptedString, String seed) {
		try {
			byte[] encryptedIvTextBytes = Base64.decodeBase64(encryptedString);
			int ivSize = 16;

			// Extract IV.
			byte[] iv = new byte[ivSize];
			System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			// Extract encrypted part.
			int encryptedSize = encryptedIvTextBytes.length - ivSize;
			byte[] encryptedBytes = new byte[encryptedSize];
			System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

			SecretKey key = getSecretKey(seed);

			// decrypt
			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			return new String(cipher.doFinal(encryptedBytes), "UTF-8");

		} catch (Throwable ex) {
			throw new BusinessRuntimeExcepion("Can not decrypt string").by(ex);
		}
	}

	private SecretKey getSecretKey(String seed) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] keyBytes = new byte[32];
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(seed.getBytes("UTF-8"));
		System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
		SecretKey key = new SecretKeySpec(keyBytes, AES);
		return key;
	}

	/**
	 * Common AES decryption
	 * 
	 * @param plainText
	 * @return
	 */
	public String encrypt(final String plainText) {
		return encrypt(plainText, KEY);
	}

	/**
	 * 
	 * @param plainText
	 * @param seed
	 * @return
	 */
	public String encrypt(final String plainText, String seed) {
		try {
			// Generating IV.
			int ivSize = 16;
			byte[] iv = new byte[ivSize];
			SecureRandom random = new SecureRandom();
			random.nextBytes(iv);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			// Encrypt
			SecretKey key = getSecretKey(seed);
			byte[] cleartext = plainText.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
			byte[] encrypted = cipher.doFinal(cleartext);

			// Combine IV and encrypted part.
			byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
			System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
			System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
			return new String(Base64.encodeBase64(encryptedIVAndText));
		} catch (Throwable ex) {
			throw new BusinessRuntimeExcepion("Can not encrypt string").by(ex);
		}
	}

//	public static void main(String[] args) {
//		String plainText = "password";
//		String seed = "FeHd!CBs@2021"; // Same as cbsapp.properties
//		System.out.println(plainText + " => " + new CryptoService().encrypt(plainText, seed));
//
//	}

}
