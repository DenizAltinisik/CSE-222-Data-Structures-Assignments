import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Directory class represents a directory in the file system.
 * Inherits from the abstract class FileSystemElement.
 */
class Directory extends FileSystemElement {
    protected LinkedList<FileSystemElement> children; // List of children elements within the directory

    /**
     * Constructor for creating a new Directory object.
     * Initializes the directory name, creation date, and parent directory.
     * Initializes an empty list for children elements.
     * @param name The name of the directory.
     * @param dateCreated The timestamp indicating the date and time when the directory was created.
     * @param parent The parent directory of the directory.
     */
    public Directory(String name, Timestamp dateCreated, Directory parent) {
        super(name, dateCreated, parent);
        children = new LinkedList<>();
    }

    /**
     * Adds a child element to the directory.
     * @param child The child element to add.
     */
    public void addChildren(FileSystemElement child) {
        children.add(child);
    }

    /**
     * Removes a child element from the directory.
     * @param child The child element to remove.
     */
    public void removeChildren(FileSystemElement child) {
        children.remove(child);
    }

    /**
     * Returns the list of children elements within the directory.
     * @return The list of children elements.
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Changes the name of the directory.
     * @param name The new name of the directory.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * Changes the creation date of the directory.
     * @param name The new creation date of the directory.
     */
    public void changeDataCreated(String name) {
        this.name = name;
    }

    /**
     * Displays information about the directory.
     * Overrides the displayInfo method in the parent class.
     */
    @Override
    public void displayInfo() {
        System.out.println("Directory Name: " + name);
        System.out.println("Date Created: " + dateCreated);
    }
}
