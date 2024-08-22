import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Arrays to store orders, customers, and operators
        order[] orders = new order[100];
        customer[] customers = new customer[100];
        operator[] operators = new operator[100];

        // Index counters for each array
        int orderIndex = 0;
        int customerIndex = 0;
        int operatorIndex = 0;

        try {
            File file = new File("content.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] data = line.split(";"); // split line to words
                int numberOfSemicolons = line.length() - line.replace(";", "").length(); // calculate number of semicolons
                // Validate the data format
                if (data.length < 2 || data.length > 8) {
                    System.err.println("Invalid data format: " + line);
                    continue; // Skip to the next line
                } // missing line member error

                // Check for float numbers where only integers are expected

                // Check if the first element is a valid object type
                try {
                    switch (data[0]) {
                        case "order":

                            // EXCEPTION PARTS
                            if(numberOfSemicolons < 5)
                            {
                                throw new Exception("Missing semicolon.");
                            }
                            if(numberOfSemicolons > 5)
                            {
                                throw new Exception("Unnecessary semicolon usage.");

                            }
                            if (data.length != 6) {
                                throw new Exception("Too many or missing data");
                            } // missing or extra column

                            if(data[1] == "" || data[2] == "" || data[3] == "" || data[4] == "" || data[5] == ""){
                                throw new Exception("Empty string found.");
                            }
                            if(Integer.parseInt(data[2]) <= 0 || Integer.parseInt(data[2]) > Integer.MAX_VALUE || Integer.parseInt(data[3]) <= 0 || Integer.parseInt(data[3]) > Integer.MAX_VALUE || Integer.parseInt(data[4]) < 0 || Integer.parseInt(data[4]) > 3 || Integer.parseInt(data[5]) <= 0 || Integer.parseInt(data[5]) > Integer.MAX_VALUE){
                                throw new Exception("Invalid integer format");
                            } // invalid integer error handling

                            for (int i = 2; i < data.length; i++) {
                                try {
                                    int intValue = Integer.parseInt(data[i]);
                                } catch (NumberFormatException e) {
                                    System.err.println("Invalid number format. Only integers accepted: " + line);
                                    break; // Stop checking further and move to the next line
                                }
                            } // inconvertible number error

                            // END OF EXCEPTION PARTS

                            // Create and store the order
                            orders[orderIndex] = new order(data[1], Integer.parseInt(data[2]),
                                    Integer.parseInt(data[3]), Integer.parseInt(data[4]),
                                    Integer.parseInt(data[5]));
                            orderIndex++;
                            break;

                        case "retail_customer":
                            // EXCEPTION PARTS
                            if(numberOfSemicolons < 6)
                            {
                                throw new Exception("Missing semicolon.");
                            }
                            if(numberOfSemicolons > 6)
                            {
                                throw new Exception("Unnecessary semicolon usage.");

                            }
                            if (data.length != 7) {
                                throw new Exception("Invalid retail customer data format");
                            }
                            if(data[1] == "" || data[2] == "" || data[3] == "" || data[4] == ""){
                                throw new Exception("Empty string found.");
                            }
                            if( Integer.parseInt(data[5]) <= 0 || Integer.parseInt(data[6]) <= 0){
                                throw new Exception("Invalid integer format");
                            } // invalid integer error handling
                            int allDigits = 1;
                            int isFirstPlus = 0;
                            if(data[4].charAt(0) == '+')
                                isFirstPlus = 1;
                            for (int i = 1; i < data[4].length(); i++) {
                                if (!Character.isDigit(data[4].charAt(i))) {
                                    allDigits = 1;
                                    break;
                                }
                            }
                            if(allDigits == 0 || isFirstPlus == 0 || data[4].length() != 13)
                            {
                                throw new Exception("Invalid phone number.");
                            }
                            for (int i = 5; i < data.length; i++) {
                                try {
                                    int intValue = Integer.parseInt(data[i]);
                                } catch (NumberFormatException e) {
                                    System.err.println("Invalid number format. Only integers accepted: " + line);
                                    break; // Stop checking further and move to the next line
                                }
                            } // Integer is not convertible
                            for(int k = 0; k < customerIndex; k++){
                                if(Integer.parseInt(data[5]) == customers[k].getID())
                                {
                                    throw new Exception("Customer with same ID exists.");
                                }
                            } // same id error handling
                            // END OF EXCEPTION PARTS


                            // Create and store the retail customer
                            customers[customerIndex] = new retail_customer(data[1], data[2], data[3],
                                    data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                            customerIndex++;
                            break;

                        case "corporate_customer":
                            // EXCEPTION PARTS
                            if(numberOfSemicolons < 7)
                            {
                                throw new Exception("Missing semicolon.");
                            }
                            if(numberOfSemicolons > 7)
                            {
                                throw new Exception("Unnecessary semicolon usage.");

                            }
                            if (data.length != 8) {
                                throw new Exception("Invalid corporate customer data format");
                            }
                            if(data[1] == "" || data[2] == "" || data[3] == "" || data[4] == "" || data[7] == ""){
                                throw new Exception("Empty string found.");
                            }
                            if( Integer.parseInt(data[5]) <= 0 || Integer.parseInt(data[6]) <= 0){
                                throw new Exception("Invalid integer format");
                            } // invalid integer error handling
                            allDigits = 1;
                            isFirstPlus = 0;
                            if(data[4].charAt(0) == '+')
                                isFirstPlus = 1;
                            for (int i = 1; i < data[4].length(); i++) {
                                if (!Character.isDigit(data[4].charAt(i))) {
                                    allDigits = 0;
                                    break;
                                }
                            } // integer part cannot be recognized part
                            if(allDigits == 0 || isFirstPlus == 0 || data[4].length() != 13)
                            {
                                throw new Exception("Invalid phone number.");
                            } // phone number check

                            for (int i = 5; i < data.length-1; i++) {
                                try {
                                    int intValue = Integer.parseInt(data[i]);
                                } catch (NumberFormatException e) {
                                    System.err.println("Invalid number format. Only integers accepted: " + line);
                                    break; // Stop checking further and move to the next line
                                }
                            } // Integer is not convertible
                            for(int k = 0; k < customerIndex; k++){
                                if(Integer.parseInt(data[5]) == customers[k].getID())
                                {
                                    throw new Exception("Customer with same ID exists.");
                                }
                            } // same id error handling
                            // END OF EXCEPTION PARTS


                            // Create and store the corporate customer
                            customers[customerIndex] = new corporate_customer(data[1], data[2], data[3],
                                    data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7]);
                            customerIndex++;
                            break;

                        case "operator":
                            // EXCEPTION PARTS
                            if(numberOfSemicolons < 6)
                            {
                                throw new Exception("Missing semicolon.");
                            }
                            if(numberOfSemicolons > 6)
                            {
                                throw new Exception("Unnecessary semicolon usage.");

                            }
                            if (data.length != 7) {
                                throw new Exception("Invalid operator data format");
                            }
                            if(data[1] == "" || data[2] == "" || data[3] == "" || data[4] == ""){
                                throw new Exception("Empty string found.");
                            }
                            if( Integer.parseInt(data[5]) <= 0 || Integer.parseInt(data[6]) <= 0){
                                throw new Exception("Invalid integer format");
                            } // negative integer error handling

                            allDigits = 1;
                            isFirstPlus = 0;
                            if(data[4].charAt(0) == '+')
                                isFirstPlus = 1;
                            for (int i = 1; i < data[4].length(); i++) {
                                if (!Character.isDigit(data[4].charAt(i))) {
                                    allDigits = 0;
                                    break;
                                }
                            }
                            if(allDigits == 0 || isFirstPlus == 0 || data[4].length() != 13)
                            {
                                throw new Exception("Invalid phone number.");
                            }

                            for (int i = 5; i < data.length-1; i++) {
                                try {
                                    int intValue = Integer.parseInt(data[i]);
                                } catch (NumberFormatException e) {
                                    System.err.println("Invalid number format. Only integers accepted: " + line);
                                    break; // Stop checking further and move to the next line
                                }
                            } // Integer is not convertible
                            for(int k = 0; k < operatorIndex; k++){
                                if(Integer.parseInt(data[5]) == operators[k].getID())
                                {
                                    throw new Exception("Operator with same ID exists.");
                                }
                            } // same id error handling
                            // END OF EXCEPTION PARTS


                            // Create and store the operator
                            operators[operatorIndex] = new operator(data[1], data[2], data[3],
                                    data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                            operatorIndex++;
                            break;

                        default:
                            throw new Exception("Unknown identifier: " + data[0]); // order_1
                    }
                } catch (Exception e) {
                    // System.err.println("Error in line: " + li
                    // ne + " - " + e.getMessage()); //print error - not necessary
                }
            }
            scanner.close();

            // DEFINE PARTS
            for(int j = 0; j < customerIndex; j++){
                customers[j].define_orders(orders);
            }
            for(int i = 0; i < operatorIndex; i++) {
                operators[i].define_customers(customers);
            }

            System.out.println("Please enter your ID: ");
            // Create a Scanner object to read input from the console
            Scanner scanner1 = new Scanner(System.in);

            // Read the input provided by the user
            int userInput = scanner1.nextInt();
            scanner1.close();


            int notFoundFlag = 1;
            for(int i = 0; i < 100; i++) {
                if(customers[i] != null && customers[i].getID() == userInput)
                {
                    System.out.println("*** Customer Screen ***");
                    customers[i].print_customer();
                    notFoundFlag = 0;
                    break;
                }
            } // print customer first
            for(int i = 0; i < 100; i++) {
                if(operators[i] != null && operators[i].getID() == userInput && notFoundFlag == 1)
                {
                    operators[i].print_operator();
                    notFoundFlag = 0;
                    break;
                }
            } // print operator then
            if(notFoundFlag == 1) System.out.println("No operator/customer was found with ID " + userInput + ". Please try again.");

        } catch (Exception e) {
            System.out.println("File or user input error. Please try again.");
        }
    }
}

