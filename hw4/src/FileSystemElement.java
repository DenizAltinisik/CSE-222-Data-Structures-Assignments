import java.sql.Timestamp;

/**
 * FileSystemElement is the abstract class that is inherited by directory and file subclasses.
 */
public abstract class FileSystemElement {
    /**
     * Name of the file system element
     */
    protected String name;
    /**
     * Timestamp indicating the date and time when the element was created
     */
    protected Timestamp dateCreated;
    /**
     * The parent directory of the file system element
     */
    protected Directory parent;

    /**
     * Default constructor for FileSystemElement.
     * Initializes name, dateCreated, and parent to null.
     */
    public FileSystemElement(){
        name = null;
        dateCreated = null;
        parent = null;
    }

    /**
     * Parameterized constructor for FileSystemElement.
     * @param name The name of the file system element.
     * @param dateCreated The timestamp indicating the date and time when the element was created.
     * @param parent The parent directory of the file system element.
     */
    public FileSystemElement(String name, Timestamp dateCreated, Directory parent){
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
    }

    /**
     * Returns the name of the file system element.
     * @return The name of the file system element.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the timestamp indicating the date and time when the element was created.
     * @return The timestamp indicating the date and time when the element was created.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent directory of the file system element.
     * @return The parent directory of the file system element.
     */
    public Directory getParent() {
        return parent;
    }

    /**
     * Abstract method to display information about the file system element.
     */
    public abstract void displayInfo();

    /**
     * Sets the parent directory of the file system element.
     * @param newParent The new parent directory to set.
     */
    public void setParent(Directory newParent) {
        parent = newParent;
    }
}

