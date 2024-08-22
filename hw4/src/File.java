import java.sql.Timestamp;

/**
 * File class represents a file in the file system.
 * Inherits from the abstract class FileSystemElement.
 */
class File extends FileSystemElement {

    /**
     * Constructor for creating a new File object.
     * @param name The name of the file.
     * @param dateCreated The timestamp indicating the date and time when the file was created.
     * @param parent The parent directory of the file.
     */
    public File(String name, Timestamp dateCreated, Directory parent) {
        super(name, dateCreated, parent);
    }

    /**
     * Displays information about the file.
     * Overrides the displayInfo method in the parent class.
     */
    @Override
    public void displayInfo() {
        System.out.println("File Name: " + name);
        System.out.println("Date Created: " + dateCreated);
    }
}
