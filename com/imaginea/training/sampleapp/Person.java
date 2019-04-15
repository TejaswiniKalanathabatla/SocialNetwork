/**
 * 
 */
package com.imaginea.training.sampleapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.imaginea.sample.registration.Registration;

import Services.FeedService;

/**
 * @author tejaswinik
 *
 */
public class Person {
	private PersonData persondata;
	private Address address;
	private List<String> interests;
	private List<String> hobbies;
	private List<AcademicInfo> acadamicDetails;
	private List<WorkExperience> workDetails;
	private List<Person> friends;
	private List<FriendRequest> friendRequests;

	public void addFriend(Person person) {
		if (friends == null)
			friends = new ArrayList<>();

		friends.add(person);

	}

	public String uniqueIdentifier() {
		return persondata.getEmail();
	}

	public boolean doYouHaveFriends() {
		return null != friends && !friends.isEmpty();
	}

	public List<String> namesOfTheFriends() {
			if(!Objects.isNull(friends))
					return friends.stream().map(p -> p.persondata.name()).collect(Collectors.toList());
			return null;
			
	}

	public List<Person> friends() {
		return friends;
	}

	public Person(Registration registration) {
		this.persondata = registration.getPersonalInfo();
	}

	public Person() {
	}

	public PersonData getPersonaldata() {
		return persondata;
	}

	public boolean isUserNameMatchingTo(final String username) {
		return this.persondata.isUserNameMatchingTo(username);
	}

	public boolean isEmailEqualTo(String email) {
		if (this.persondata == null)
			return false;
		return this.persondata.isEmailEqualTo(email);
	}

	@Override
	public String toString() {
		return "Person [personalInfo=" + persondata + ", address=" + address + ", interests=" + interests
				+ ", hobbies=" + hobbies + ", acadamicDetails=" + acadamicDetails + ", workDetails=" + workDetails
				+ ", friends=" + friends + ", friendRequests=" + friendRequests + "]";
	}

	public static Person getInstance() {
		// TODO Auto-generated method stub
		return null;
		
	}

	
	public  static List<String> retrievePost(String username) {
        if(username==null)
            throw new RuntimeException("give username");
        return FeedService.getInstance().retrievePost(username);
}
}

	
	
	
	
	
	
	
	
	
	

