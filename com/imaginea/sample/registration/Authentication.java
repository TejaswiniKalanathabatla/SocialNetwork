package com.imaginea.sample.registration;

import com.imaginea.data.AuthenticationData;

public class Authentication {
	String username;
	String password;
	public static boolean authenticate(String username, String password) {
		return AuthenticationData.INSTANCE.isUserNamePasswordMatching(username, password);
	}

	public static boolean logout(String username) {
		// Process logout
		return true;
	}

}
