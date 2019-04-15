package com.imaginea.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public enum AuthenticationData {
	INSTANCE;

	private static final String MD5 = "MD5";
	private Map<String, String> userCredentialsMap = new HashMap<>();

	public void store(final String username, final String password) {
		userCredentialsMap.put(username, encrypt(password));
	}

	private String encrypt(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(MD5);
			byte[] byteArray = digest.digest(password.getBytes());
			return new String(byteArray);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return password;
	}

	public boolean isUserNameAlreadyExists(final String username) {
		return userCredentialsMap.containsKey(username);
	}

	public boolean isUserNamePasswordMatching(final String username, final String password) {

		if (isUserNameAlreadyExists(username))
			return encrypt(password).equals(userCredentialsMap.get(username));

		return false;
	}

	public void toCheckIfTheGivenUserIsPresent(String username, String password) {
		
		
	}

}


