package com.imaginea.sample.registration;

import com.imaginea.data.UserRepository;
import com.imaginea.training.sampleapp.PersonData;

public class Registration {
	private final PersonData personalInfo;
	private final String password;

	public Registration(PersonData personalInfo, String password) {
		super();
		this.personalInfo = personalInfo;
		this.password = password;
	}

	public PersonData getPersonalInfo() {
		return personalInfo;
	}
	
}
		

	
	
	


