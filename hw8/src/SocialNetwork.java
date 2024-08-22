import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Represents a social network with people and their friendships.
 */
public class SocialNetwork {
    private Map<String, Person> people; // Map of people keyed by their names
    private Map<String, List<Person>> graph; // Adjacency list representing friendships
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * Constructs a new SocialNetwork object.
     */
    public SocialNetwork() {
        this.people = new HashMap<>();
        this.graph = new HashMap<>();
    }

    /**
     * Adds a person to the social network.
     *
     * @param name      The name of the person.
     * @param age       The age of the person.
     * @param hobbies   The hobbies of the person.
     * @param timestamp The timestamp when the person joined the network.
     */
    public void addPerson(String name, int age, List<String> hobbies, LocalDateTime timestamp) {
        Person person = new Person(name, age, hobbies, timestamp);
        String key = name + "_" + timestamp.format(formatter);
        people.put(key, person);
        graph.put(key, new ArrayList<>());
        System.out.println("Person added: " + name + " (Timestamp: " + timestamp.format(formatter) + ")");
    }

    /**
     * Removes a person from the social network.
     *
     * @param name      The name of the person to be removed.
     * @param timestamp The timestamp when the person joined the network.
     */
    public void removePerson(String name, LocalDateTime timestamp) {
        String key = name + "_" + timestamp.format(formatter);
        Person person = people.remove(key);
        if (person != null) {
            graph.remove(key);
            for (List<Person> friends : graph.values()) {
                friends.remove(person);
            }
            System.out.println("Person removed: " + name + " (Timestamp: " + timestamp.format(formatter) + ")");
        } else {
            System.out.println("Person not found.");
        }
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param name1      The name of the first person.
     * @param timestamp1 The timestamp when the first person joined the network.
     * @param name2      The name of the second person.
     * @param timestamp2 The timestamp when the second person joined the network.
     */
    public void addFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        String key1 = name1 + "_" + timestamp1.format(formatter);
        String key2 = name2 + "_" + timestamp2.format(formatter);
        System.out.println("Add friendship keys: " + key1 + ", " + key2); // Debugging statement
        Person person1 = people.get(key1);
        Person person2 = people.get(key2);
        if (person1 != null && person2 != null) {
            graph.get(key1).add(person2);
            graph.get(key2).add(person1);
            System.out.println("Friendship added between " + name1 + " and " + name2);
        } else {
            System.out.println("One or both persons not found.");
        }
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param name1      The name of the first person.
     * @param timestamp1 The timestamp when the first person joined the network.
     * @param name2      The name of the second person.
     * @param timestamp2 The timestamp when the second person joined the network.
     */
    public void removeFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        String key1 = name1 + "_" + timestamp1.format(formatter);
        String key2 = name2 + "_" + timestamp2.format(formatter);
        System.out.println("Remove friendship keys: " + key1 + ", " + key2); // Debugging statement
        Person person1 = people.get(key1);
        Person person2 = people.get(key2);
        if (person1 != null && person2 != null) {
            graph.get(key1).remove(person2);
            graph.get(key2).remove(person1);
            System.out.println("Friendship removed between " + name1 + " and " + name2);
        } else {
            System.out.println("One or both persons not found.");
        }
    }

    /**
     * Finds the shortest path between two people in the social network.
     *
     * @param name1      The name of the start person.
     * @param timestamp1 The timestamp when the start person joined the network.
     * @param name2      The name of the end person.
     * @param timestamp2 The timestamp when the end person joined the network.
     * @return A list representing the shortest path between the two people.
     */
    public List<String> findShortestPath(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        String key1 = name1 + "_" + timestamp1.format(formatter);
        String key2 = name2 + "_" + timestamp2.format(formatter);
        System.out.println("Shortest path keys: " + key1 + ", " + key2); // Debugging statement
        if (!people.containsKey(key1) || !people.containsKey(key2)) {
            return Collections.emptyList();
        }

        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> previous = new HashMap<>();
        Set<Person> visited = new HashSet<>();

        Person start = people.get(key1);
        Person end = people.get(key2);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            if (current.equals(end)) {
                break;
            }
            for (Person neighbor : graph.get(current.getName() + "_" + current.getTimestamp().format(formatter))) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<String> path = new LinkedList<>();
        for (Person at = end; at != null; at = previous.get(at)) {
            path.add(0, at.getName());
        }
        if (path.get(0).equals(start.getName())) {
            return path;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Suggests friends for a given person based on mutual friends and common hobbies.
     * Calculates a score for each potential friend and sorts them in descending order of score.
     * Returns a list of suggested friends with a detailed breakdown of the score.
     *
     * @param name           The name of the person.
     * @param timestamp      The timestamp when the person joined the network.
     * @param maxSuggestions The maximum number of friends to suggest.
     * @return A list of suggested friends with a detailed breakdown of the score.
     */
    public List<String> suggestFriends(String name, LocalDateTime timestamp, int maxSuggestions) {
        String key = name + "_" + timestamp.format(formatter);
        if (!people.containsKey(key)) {
            return Collections.emptyList();
        }

        Person person = people.get(key);
        Map<Person, Double> scoreMap = new HashMap<>();

        for (Person potentialFriend : people.values()) {
            if (!potentialFriend.equals(person) && !graph.get(key).contains(potentialFriend)) {
                double score = 0;
                int mutualFriends = countMutualFriends(person, potentialFriend);
                int commonHobbies = countCommonHobbies(person, potentialFriend);
                score = mutualFriends + 0.5 * commonHobbies;
                if (score > 0) {
                    scoreMap.put(potentialFriend, score);
                }
            }
        }

        List<Map.Entry<Person, Double>> suggestions = new ArrayList<>(scoreMap.entrySet());
        suggestions.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(maxSuggestions, suggestions.size()); i++) {
            Person suggestedFriend = suggestions.get(i).getKey();
            double score = suggestions.get(i).getValue();
            int mutualFriends = countMutualFriends(person, suggestedFriend);
            int commonHobbies = countCommonHobbies(person, suggestedFriend);
            result.add(suggestedFriend.getName() + " (Score: " + score + ", " +
                    mutualFriends + " mutual friends, " + commonHobbies + " common hobbies)");
        }
        return result;
    }

    /**
     * Counts the number of mutual friends between two persons.
     *
     * @param person1 The first person.
     * @param person2 The second person.
     * @return The number of mutual friends between the two persons.
     */
    private int countMutualFriends(Person person1, Person person2) {
        Set<Person> friends1 = new HashSet<>(graph.get(person1.getName() + "_" + person1.getTimestamp().format(formatter)));
        Set<Person> friends2 = new HashSet<>(graph.get(person2.getName() + "_" + person2.getTimestamp().format(formatter)));
        friends1.retainAll(friends2);
        return friends1.size();
    }


    /**
     * Counts the number of common hobbies between two persons.
     *
     * @param person1 The first person.
     * @param person2 The second person.
     * @return The number of common hobbies between the two persons.
     */
    private int countCommonHobbies(Person person1, Person person2) {
        List<String> hobbies1 = person1.getHobbies();
        List<String> hobbies2 = person2.getHobbies();
        int count = 0;
        for (String hobby : hobbies1) {
            if (hobbies2.contains(hobby)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Counts the clusters in the social network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;
        List<List<String>> clusters = new ArrayList<>();

        for (Person person : people.values()) {
            if (!visited.contains(person)) {
                clusterCount++;
                List<String> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusters.add(cluster);
            }
        }

        System.out.println("Number of clusters found: " + clusterCount);
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster " + (i + 1) + ":");
            for (String name : clusters.get(i)) {
                System.out.println(name);
            }
        }
    }
    /**
     * Performs a breadth-first search (BFS) to find the shortest path between two people
     * in the social network.
     *
     * @param start      The starting person for the search.
     * @param visited    A set to keep track of visited people during the search.
     * @param cluster    A list to store the names of people in the current cluster.
     */
    private void bfs(Person start, Set<Person> visited, List<String> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person person = queue.poll();
            cluster.add(person.getName());
            for (Person neighbor : graph.get(person.getName() + "_" + person.getTimestamp().format(formatter))) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}
