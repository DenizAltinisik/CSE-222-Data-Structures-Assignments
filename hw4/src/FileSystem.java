import java.sql.Timestamp;
import java.util.*;
/**
 * FileSystem class represents a file system.
 * It provides functionality for managing directories and files.
 */
public class FileSystem {
    private Directory root;
    private Directory currentDirectory;
    /**
     * Constructor for creating a new FileSystem object.
     * Initializes the root directory as "root" and sets the current directory to root.
     */
    public FileSystem() {
        this.root = new Directory("root", new Timestamp(System.currentTimeMillis()), null);
        this.currentDirectory = root;
    }
    /**
     * Gets the current working directory.
     * @return The current working directory.
     */
    public Directory getCurrentDirectory() {
        return currentDirectory;
    }
    /**
     * Sets the current working directory.
     * @param directory The directory to set as the current working directory.
     */
    public void setCurrentDirectory(Directory directory) {
        this.currentDirectory = directory;
    }
    /**
     * Creates a new file in the current working directory.
     * @param name The name of the new file to create.
     */
    public void createFile(String name) {
        File newFile = new File(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
        currentDirectory.addChildren(newFile);
    }
    /**
     * Creates a new directory in the current working directory.
     * @param name The name of the new directory to create.
     */
    public void createDirectory(String name) {
        Directory newDirectory = new Directory(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
        currentDirectory.addChildren(newDirectory);
    }

    /**
     * Deletes a file system element (file or directory).
     * If the element is a directory, deletes all its children recursively.
     * @param element The file system element to delete.
     */
    private void deleteFileSystemElement(FileSystemElement element) {
        if (element != null) {
            // Check if the element is a directory
            if (element instanceof Directory) {
                // Recursively delete all children of the directory
                deleteChildren((Directory) element);
            }
            // Remove the element from its parent's children list
            element.getParent().removeChildren(element);
            if(element instanceof Directory)
                System.out.println("Directory deleted: " + element.getName());
            else System.out.println("File deleted: " + element.getName());

        }
    }
    /**
     * Recursively deletes all children of a directory.
     * @param element The directory whose children will be deleted.
     */
    private void deleteChildren(Directory element) {
        if (element != null) {
            return;
        }
        LinkedList<FileSystemElement> children = element.getChildren();
        for(FileSystemElement child : children) {
            if (child instanceof Directory) {
                deleteChildren((Directory) child);
            }
            element.removeChildren(child);
        }

    }
    /**
     * Moves a file system element to a new parent directory.
     * @param element The file system element to move.
     * @param newParentPath The path of the new parent directory.
     */
    public void moveFileSystemElement(FileSystemElement element, String newParentPath) {
        Directory newParent = null;
        // Ensure the newParentPath starts with "/root"
        if (!newParentPath.startsWith("/root")) {
            System.out.println("Invalid directory path.1111");
            return;
        }
        else newParent = root; // user entered root directory to move file inside of it.
        // Split the path into directory names
        String[] directories = newParentPath.split("/");
        Directory current = root;
        for (int i = 2; i < directories.length; i++) { // Start from index 2 to skip empty string and "root"
            String directoryName = directories[i];
            newParent = (Directory) search(directoryName, current);

            if (newParent != null && newParent instanceof Directory) {
                current = newParent;
            } else {
                System.out.println("Invalid directory path.");
                return;
            }
        }

        // Move the element to the new parent directory
        if (element != null && newParent != null) {
            currentDirectory.removeChildren(element);
            element.setParent(newParent);
            newParent.addChildren(element);
            System.out.println("File or directory moved successfully.");
        } else {
            System.out.println("File or directory not found.");
        }
    }

    /**
     * Displays the current path.
     * @param x The directory whose path will be displayed.
     * @param isSearch An indicator (0 or 1) to distinguish normal path display and search result path display.
     */
    public void displayCurrentPath(Directory x, int isSearch) {
        LinkedList<String> path = new LinkedList<>();
        Directory current = x;
        while (current != null) {
            path.addFirst(current.getName());
            current = current.getParent();
        }
        if(isSearch == 0) System.out.print(" /" + String.join("/", path));
        else if(isSearch == 1) System.out.print("Found: /" + String.join("/", path));
    }
    /**
     * Searches for a file system element (file or directory) by name.
     * @param name The name of the element to search for.
     * @param directory The directory to search within.
     * @return The file system element if found, null otherwise.
     */
    public FileSystemElement search(String name, Directory directory) {
        LinkedList<FileSystemElement> children = directory.getChildren();
        for (FileSystemElement child : children) {
            if (child.getName().equals(name)) {
                return child;
            } else if (child instanceof Directory) {
                FileSystemElement found = search(name, (Directory) child); // recursively search children
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
    /**
     * Prints the directory tree structure from the specified directory to the root directory,
     * including the contents of the specified directory.
     *
     * @param x The directory that will be displayed as tree node from root.
     */
    public void printDirectoryTree(Directory x) {
        LinkedList<String> path = new LinkedList<>();
        Directory current = x;

        // Traverse from the current directory to the root directory
        while (current != null) {
            path.addFirst(current.getName());
            current = current.getParent();
        }

        // Print each directory in the path with increasing tab spaces

        for (int i = 0; i < path.size(); i++) {
            String directoryName = path.get(i);
            String tabs = " ".repeat(i); // Generate tab spaces based on the directory level
            if(i != path.size() - 1)
            System.out.println(tabs + "*" + directoryName + "/");
            else System.out.print(tabs + "*" + directoryName + "/");
        }
        System.out.println("(current directory)");
        // Print the contents of the current directory

        LinkedList<FileSystemElement> children = x.getChildren();
        String tabs = " ".repeat(path.size());
        for (FileSystemElement child : children) {
            if (child instanceof Directory) {

                System.out.println(tabs + "*" + child.getName() + "/");
            } else {
                System.out.println(tabs + child.getName());
            }
        }
    }

    /**
     * Lists the contents (files and directories) of the current directory.
     */
    public void listContents() {
        LinkedList<FileSystemElement> children = currentDirectory.getChildren();
        for (FileSystemElement child : children) {
            if (child instanceof Directory) {
                System.out.println("*" + child.getName() + "/");
            } else {
                System.out.println(child.getName());
            }
        }
    }
    /**
     * Navigates to a specified directory.
     * @param newPath The path of the directory to navigate to.
     */
    private void navigateToDirectory(String newPath) {
        // Ensure the path starts with "/root"
        if (!newPath.startsWith("/root")) {
            System.out.println("Invalid directory path.");
            return;
        }

        // Split the path into directory names
        String[] directories = newPath.split("/");
        Directory current = root;

        // Traverse each directory in the path
        for (int i = 2; i < directories.length; i++) { // Start from index 2 to skip empty string and "root"
            String directoryName = directories[i];
            FileSystemElement element = search(directoryName, current);

            if (element instanceof Directory) {
                current = (Directory) element;
            } else {
                System.out.println("Invalid directory path.");
                return;
            }
        }

        // Update the current directory and display the new path
        setCurrentDirectory(current);
        System.out.print("Directory changed to: ");
        displayCurrentPath(currentDirectory, 0);
    }
    /**
     * Sorts the files within a directory by creation date and prints them.
     * @param directory The directory whose files will be sorted and printed.
     */
    public void sortFilesByCreationDate(Directory directory) {
        LinkedList<FileSystemElement> children = directory.getChildren();
        Collections.sort(children, Comparator.comparing(FileSystemElement::getDateCreated));
        for (FileSystemElement child : children) {
            if (child instanceof Directory)
            System.out.println("*" + child.getName() + "/ (" + child.getDateCreated() + ")");
            else System.out.println(child.getName() + " (" + child.getDateCreated() + ")");
        }
}

    /**
     * Starts an interactive command-line interface for system operations.
     * Provides a menu for users to perform various file system operations.
     */
    public void startCLI() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("\n\n\n===== File System Management Menu =====");
            System.out.println("1 Changing Directory");
            System.out.println("2 Listing Directory Contents");
            System.out.println("3 Creating Files and Directories");
            System.out.println("4 Deleting Files and Directories");
            System.out.println("5 Moving Files and Directories");
            System.out.println("6 Searching for Files and Directories");
            System.out.println("7 Printing the Directory Tree");
            System.out.println("8 Sorting Directory Contents");
            System.out.println("9. Exit");

            System.out.print("Please select an option: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Current directory: ");
                    displayCurrentPath(currentDirectory, 0);
                    System.out.print("\nEnter new directory path: ");
                    String newPath = scanner.nextLine();
                    navigateToDirectory(newPath);
                    break;
                case "2":
                    System.out.print("Listing contents of ");
                    displayCurrentPath(currentDirectory,0);
                    System.out.print("\n");
                    listContents();
                    break;
                case "3":
                    System.out.print("Current directory: ");
                    displayCurrentPath(currentDirectory, 0);
                    System.out.println("\nCreate file or directory (f/d): ");
                    String choice = scanner.nextLine();
                    if (choice.equals("d")) {
                        System.out.println("Enter name for new directory: ");
                        String dirName = scanner.nextLine();
                        createDirectory(dirName);
                    } else if (choice.equals("f")) {
                        System.out.print("Enter name for new file: ");
                        String fileName = scanner.nextLine();
                        createFile(fileName);
                    } else {
                        System.out.println("Invalid choice. Please enter 'f' for file or 'd' for directory.");
                    }
                    break;
                case "4":
                    System.out.print("Current directory: ");
                    displayCurrentPath(currentDirectory, 0);
                    System.out.print("\nEnter name of file/directory to delete: ");
                    String deleteName = scanner.nextLine();
                    FileSystemElement deleteElement = search(deleteName, currentDirectory);
                    if (deleteElement != null) {
                        deleteFileSystemElement(deleteElement);
                    } else {
                        System.out.println("File or directory not found.");
                    }
                    break;
                case "5":
                    System.out.print("Current directory: ");
                    displayCurrentPath(currentDirectory, 0);
                    System.out.print("\nEnter the name of file/directory to move: ");
                    String moveName = scanner.nextLine();
                    FileSystemElement moveElement = search(moveName, currentDirectory);
                    if (moveElement != null) {
                        System.out.print("Enter new directory path: ");
                        String movePath = scanner.nextLine();
                        // Ensure the movePath starts with "/root"
                        if (!movePath.startsWith("/root")) {
                            System.out.println("Invalid directory path.2222");
                        } else {
                            moveFileSystemElement(moveElement, movePath);
                        }
                    } else {
                        System.out.println("File or directory not found.");
                    }
                    break;

                case "6":
                    System.out.print("Search query: ");
                    String query = scanner.nextLine();
                    FileSystemElement e = search(query, root);
                    if (e != null) {
                        System.out.println("Search from root...");
                        displayCurrentPath(e.getParent(), 1);
                        System.out.print( "/" + e.getName());
                    }
                    else {
                        System.out.println("File or directory not found.");
                    }
                    break;
                case "7":
                    System.out.println("Path to current directory from root:");
                    printDirectoryTree(currentDirectory);
                    break;
                case "8":
                    System.out.print("Sorting contents of ");
                    displayCurrentPath(currentDirectory, 0);
                    System.out.print(" by date created:\n");
                    sortFilesByCreationDate(currentDirectory);
                    break;
                case "9":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command. Please select a valid option.");
                    break;
            }
        }
    }

}