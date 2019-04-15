package SocialNetwork;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.imaginea.data.AuthenticationData;
import com.imaginea.data.UserRepository;
import com.imaginea.sample.registration.Authentication;
import com.imaginea.sample.registration.Registration;
import com.imaginea.training.sampleapp.Gender;
import com.imaginea.training.sampleapp.Person;
import com.imaginea.training.sampleapp.PersonData; 


import Services.FeedService;
import Services.Friends;

public class SocialNetwork {
	static Scanner scanner = new Scanner(System.in);
    private static Authentication authentication = new Authentication();
    private static Map<String, List<String> >userRequest = new HashMap<>();

    public static void main(String args[]) {

        while (true) {
            choosingOptions();
            operationOnInput();
        }
    }

    private static void choosingOptions() {

        System.out.println(" choose the following from the menu....!");
        System.out.println(" 1. Login \n 2. Registration");
    }

    private static void operationOnInput() {
        int input = scanner.nextInt();
        switch (input) {
        case 1:
            loginServices();
            break;
        case 2:
            registrationProcess();
            break;

        }
    }

    private static void loginServices() {
        System.out.println(" please enter the username and password");
        String username = scanner.next();
        String password = scanner.next();
        if (Authentication.authenticate(username, password)) {
            postLogin(username);
        } else {
            System.out.println("  Login failed  ");
        }
    }
    
    private static void registrationProcess() {
        System.out.println("Registration process is initiated. Please provide below details");
        System.out.println("Phone:");
        String phone = scanner.next();
        System.out.println("E-Mail:");
        String email = scanner.next();
        System.out.println("firstName:");
        String firstName = scanner.next();
        System.out.println("lastName:");
        String lastName = scanner.next();
        System.out.println("Gender M/F:");
        String userGenderInput = scanner.next();
        Gender gender = Gender.MALE;
        if (userGenderInput.equals("F"))
            gender = Gender.FEMALE;
        System.out.println("Date of Birth (dd/mm/YYYY):");
        String dob = scanner.next();
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("password:");
        String password = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PersonData personInfo = new PersonData(phone, email, username, firstName, lastName, gender, dateOfBirth);
        Registration registration = new Registration(personInfo, password);
        register(registration, username, password);

    }


    public static void postLogin(String username) {
        System.out.println(" login sucess ");
        System.out.println("  chose from the options given below.");

        System.out.println("1. Home");
        System.out.println("2. Send Requests");
        System.out.println("3. Friends");
        System.out.println("4. add a new Post");
        System.out.println("5. showAndAcceptFriendRequests");
        System.out.println("6. Logout");
        switch (scanner.nextInt()) {
        case 1:
            Home(username);
            break;
        case 2:
        	displayAllFriends(username);   
            break;
        case 3:
        	sendFriendRequest(username);
            break;   	
        case 4:
        	showAndAcceptRequest(username);
            break;
        case 5:
        	addANewPost(username);
            break;
        case 6:
            logout(username);
            break;
        default:
            System.out.println(" enter valid choice");
            break;

        }

    }
    private static List<String>Home(String username) {
        FeedService feedServices = new FeedService();
        List<String> feedDataByDefault = feedServices.allPost(username);
        Print(feedDataByDefault);
        List<String>postsByOthers = Person.getInstance().retrievePost(username);
        return postsByOthers;
    }

    private static void Print(List<String> feedDataByDefault) {
		
		
	}

	private static void displayAllFriends(String username) {
    	Friends friendService = new Friends();
		friendService.getMyfriends(username);
		print(friendService.toString());
		postLogin(username);
}
    private static void print(String username) {
		
		
	}

	private static void sendFriendRequest(String username) {
        List<String>  friendslist = new ArrayList<>();      
        System.out.println(" enter usernames of friend");
        String nameofthereceiver = scanner.next();
       
        friendslist.add(nameofthereceiver); 
        Collection<Person> myuser = UserRepository.getInstance().availableUsers();
        for(Person person:myuser)
        {
            if(person.isUserNameMatchingTo(nameofthereceiver))
               
               sendrequest(username, friendslist);
        }
	}
        private static void sendrequest(String username, List<String>  nameofthereceiver) {

                UserRepository usersRepository = UserRepository.getInstance();
                addRequest(username, nameofthereceiver);
                System.out.println(" sent sucessfully to "+ nameofthereceiver);
        }
        
        public  static void addRequest(String username, List<String> friendname) {
            userRequest.put(username, friendname);
        }   
       
    

	private static void showAndAcceptRequest(String username) {
          List<String> value = userRequest.get(username);
           for(String values:value)
           {
               System.out.println(values);
           }
           }
           private static void acceptRequest(String username) {
       		Friends friendaccept = new Friends();
       		friendaccept.accept(username);
       
    }
	
    
    private static void addANewPost(String username) {
        System.out.println(" please enter the post message");
        String message = scanner.next();
        savePostToRepository(message);

    }

    private static void savePostToRepository(String message) {
        UserRepository usersRepository = UserRepository.getInstance();
        usersRepository.addpost(Person.getInstance(), message);
        System.out.println(" Successfully added");

    }


    
    
    
    
    private static void logout(String username) {
        if (Authentication.logout(username)) {
            System.out.println(" logout sucess.!");

        } else {
            System.out.println(" logout failed....!");
        }

    }

   

    private static void print(List<String> strings) {
        System.out.println(strings);

    }

    
    
    
    
    
    private static void register(Registration registration, String username, String password) {

        
        List<String> errors = validate(registration);

        if (!errors.isEmpty()) {
            errors.forEach(System.out::println);
            return;
        }

              saveToRepository(registration);
        storeUserCredentials(username, password);
        System.out.println("Account created successfully");
    }

    private static void storeUserCredentials(String username, String password) {
        AuthenticationData.INSTANCE.toCheckIfTheGivenUserIsPresent(username, password);
    }

    private static UserRepository saveToRepository(Registration registration) {
        UserRepository usersRepository = UserRepository.getInstance();
        usersRepository.addUser(new Person(registration));
        return usersRepository;
    }

    private static List<String> validate(Registration registration) {
        List<String> errors = new ArrayList<>();
        if (isUsernameAlreadyExists(registration.getPersonalInfo().getUsername()))
            errors.add("Username already exists");

        if (isEmailAlreadyExists(registration.getPersonalInfo().getEmail()))
            errors.add("Email already exists");

        return errors;
    }

    private static boolean isEmailAlreadyExists(String email) {
        return false;
    }

    private static boolean isUsernameAlreadyExists(final String username) {
        return AuthenticationData.INSTANCE.isUserNameAlreadyExists(username);
    }
}


	
	
	

