import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an individual in the social network.
 */
public class Person {
    private String name;
    private int age;
    private List<String> hobbies;
    private LocalDateTime timestamp;

    /**
     * Constructs a Person object with the given parameters.
     *
     * @param name      The person's name.
     * @param age       The person's age.
     * @param hobbies   A list of the person's hobbies.
     * @param timestamp The date and time when the person joined the network.
     */
    public Person(String name, int age, List<String> hobbies, LocalDateTime timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = timestamp;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the person.
     *
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the hobbies of the person.
     *
     * @return A list of hobbies of the person.
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Gets the timestamp when the person joined the network.
     *
     * @return The timestamp when the person joined the network.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of the person.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    /**
     * Returns a hash code value for the person.
     *
     * @return A hash code value for this person.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
