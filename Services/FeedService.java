package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FeedService {
	
	private static FeedService INSTANCE = new FeedService();
	private static 		Map<String ,List<String>> userPost= new HashMap<>();
	public void addingPost(String username) {
		Scanner scanner = new Scanner(System.in);
		List<String> messagelist = new ArrayList<>();
		
			System.out.println(" please enter the post message");
			String message = scanner.next();
			messagelist.add(message);
			userPost.put(username, messagelist);

	}

	public List<String> retrievePost(String username) {
		return  userPost.get(username);
	}
	public List<String> allPost(String username){
		Friends myservice= new Friends().getInstance();
		return null;
		
	}

	public static FeedService getInstance() {
return INSTANCE;
	}



   
		
		
	}



