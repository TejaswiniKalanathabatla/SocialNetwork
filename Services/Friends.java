package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import com.imaginea.data.PredicateContainer;
import com.imaginea.data.UserRepository;
import com.imaginea.training.sampleapp.Person;
import SocialNetwork.SocialNetwork;

public class Friends {
	SocialNetwork sc = new SocialNetwork();
	Scanner scanner = new Scanner(System.in);
	private static Friends INSTANCE = new Friends();
	private static Map<String, String> myFriendRequests = new ConcurrentHashMap<>();
	private static Map<String, List<String>> mySentRequests = new ConcurrentHashMap<>();
	private static Map<String, List<String>> friendsListMap = new ConcurrentHashMap<>();
	List<String> friendsListOfUser = new ArrayList<>();
	List<String> friendsListOfFriend = new ArrayList<>();

	public Optional<List<String>> nameOfTheFriendsOf(final String username) {

		if (PredicateContainer.STRING_EMPTY_CHECK_PREDICATE.test(username))
			throw new UserNameMustNotBeEmptyException(" user identifier is needed");
		Optional<Person> personOptionalWrapper = UserRepository.getInstance().retrievePersonBasedOnUsername(username);
		if (!personOptionalWrapper.isPresent())
			throw new UsernameDoesntExistException(" user doesn't exit in the system");
		Person person = personOptionalWrapper.get();
		return Optional.ofNullable(person.namesOfTheFriends());

	}

}

	public void accept(String username) {
		String friend = myFriendRequests.get(username);
		print(friend);
		System.out.println(" 1.  Accept \n 2. reject ");
		int answer = scanner.nextInt();
		if (answer == 1) {
			friendsListOfUser.add(friend);
			friendsListMap.put(username, friendsListOfUser);
			friendsListOfFriend.add(username);
			friendsListMap.put(friend, friendsListOfFriend);
			System.out.println("you have become friends now");
			sc.postLogin(username);
		} else
			System.out.println("rejected");
	}

	private void print(String friend) {
		System.out.println(friend);

	}

	private static void usersList() {
		List<Person> personlist = UserRepository.getInstance().ListofUsers();
		for (Person person : personlist) {
			System.out.println(person.getPersonaldata().getUsername());
		}

	}

	public List<String> getMyfriends(String username) {
		if (PredicateContainer.STRING_EMPTY_CHECK_PREDICATE.test(username))
			throw new UserNameMustNotBeEmptyException(" YOU   have no  friends YET");
		return friendsListMap.get(username);

	}

	
	public static Friends getInstance() {
		return INSTANCE();
	}

	private static Friends INSTANCE() {
		return INSTANCE();
	}


}
    