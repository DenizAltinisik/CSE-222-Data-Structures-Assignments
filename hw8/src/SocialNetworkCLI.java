import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Provides a command-line interface (CLI) for interacting with the SocialNetwork class.
 */
public class SocialNetworkCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SocialNetwork socialNetwork = new SocialNetwork();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Main method to start the CLI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    removePerson();
                    break;
                case 3:
                    addFriendship();
                    break;
                case 4:
                    removeFriendship();
                    break;
                case 5:
                    findShortestPath();
                    break;
                case 6:
                    suggestFriends();
                    break;
                case 7:
                    countClusters();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
    /**
     * Prompts the user to enter a numerical menu choice and ensures that the input is a valid integer.
     * If the input is not a valid integer, the method prompts the user again until a valid input is provided.
     *
     * @return The numerical menu choice entered by the user.
     */
    private static int getMenuChoice() {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {

            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        }
        return choice;
    }

    /**
     * Displays the main menu of the CLI.
     */
    private static void displayMenu() {
        System.out.println("===== Social Network Analysis Menu =====");
        System.out.println("1. Add person");
        System.out.println("2. Remove person");
        System.out.println("3. Add friendship");
        System.out.println("4. Remove friendship");
        System.out.println("5. Find shortest path");
        System.out.println("6. Suggest friends");
        System.out.println("7. Count clusters");
        System.out.println("8. Exit");
        System.out.print("Please select an option: ");
    }
    /**
     * Adds a person to the social network based on user input.
     */
    private static void addPerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter hobbies (comma-separated): ");
        String hobbiesInput = scanner.nextLine();
        List<String> hobbies = Arrays.asList(hobbiesInput.split(","));
        LocalDateTime timestamp = LocalDateTime.now();
        socialNetwork.addPerson(name, age, hobbies, timestamp);
    }
    /**
     * Removes a person from the social network based on user input.
     */
    private static void removePerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine(), formatter);
        socialNetwork.removePerson(name, timestamp);
    }
    /**
     * Adds a friendship between two people in the social network based on user input.
     */
    private static void addFriendship() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime timestamp1 = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime timestamp2 = LocalDateTime.parse(scanner.nextLine(), formatter);
        socialNetwork.addFriendship(name1, timestamp1, name2, timestamp2);
    }
    /**
     * Removes a friendship between two people in the social network based on user input.
     */
    private static void removeFriendship() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime timestamp1 = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime timestamp2 = LocalDateTime.parse(scanner.nextLine(), formatter);
        socialNetwork.removeFriendship(name1, timestamp1, name2, timestamp2);
    }
    /**
     * Finds the shortest path between two people in the social network based on user input.
     */
    private static void findShortestPath() {
        System.out.print("Enter start person's name: ");
        String startName = scanner.nextLine();
        System.out.print("Enter start person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime startTimestamp = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter end person's name: ");
        String endName = scanner.nextLine();
        System.out.print("Enter end person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime endTimestamp = LocalDateTime.parse(scanner.nextLine(), formatter);
        List<String> path = socialNetwork.findShortestPath(startName, startTimestamp, endName, endTimestamp);
        if (!path.isEmpty()) {
            System.out.println("Shortest path: " + String.join(" -> ", path));
        } else {
            System.out.println("No path found");
        }
    }
    /**
     * Suggests friends for a person in the social network based on user input.
     */
    private static void suggestFriends() {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter maximum number of friends to suggest: ");
        int maxSuggestions = Integer.parseInt(scanner.nextLine());
        List<String> suggestions = socialNetwork.suggestFriends(name, timestamp, maxSuggestions);
        if (!suggestions.isEmpty()) {
            System.out.println("Suggested friends: " + String.join(", ", suggestions));
        } else {
            System.out.println("No suggestions available");
        }
    }
    /**
     * Counts the clusters in the social network.
     */
    private static void countClusters() {
        socialNetwork.countClusters();
    }
}
