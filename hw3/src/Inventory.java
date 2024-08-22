import java.time.LocalDateTime;
import java.util.*;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
/**
 * Represents an inventory system for managing electronic devices.
 */

public class Inventory {
    private final LinkedList<ArrayList<Device>> devices;

    /**
     * Constructs an empty inventory with five categories of devices.
     */
    public Inventory() {
        devices = new LinkedList<>();
        // Limit the linked list to have only 5 nodes
        devices.add(new ArrayList<>()); // TV
        devices.add(new ArrayList<>()); // Smartphone
        devices.add(new ArrayList<>()); // Laptop
        devices.add(new ArrayList<>()); // Smartwatch
        devices.add(new ArrayList<>()); // Headphone
    }
    /**
     * Adds a device to the inventory, Time complexity: O(n): n is number of categories.
     * @param device The device to be added.
     */
    public void addDevice(Device device) {
        String category = device.getCategory();
        switch (category) {
            case "TV":
                devices.get(0).add(device);
                break;
            case "Smart Phone":
                devices.get(1).add(device);
                break;
            case "Laptop":
                devices.get(2).add(device);
                break;
            case "Smart Watch":
                devices.get(3).add(device);
                break;
            case "Headphones":
                devices.get(4).add(device);
                break;
            default:
                System.out.println("Invalid category.");
                break;
        }
    }
    /**
     * Removes a device from the inventory by name, Time complexity: O(5*n) = O(n).
     * @param name The name of the device to be removed.
     */
    public void removeDevice(String name) {
        int i = 0;
        boolean found = false;
        for(; i < 5; i++){
            for(int j = 0; j < devices.get(i).size(); j++) {
                if (Objects.equals(devices.get(i).get(j).getName(), name)) {
                    found = true;
                    System.out.println(devices.get(i).get(j).getName() + " was removed successfully.");
                    devices.get(i).remove(j);

                    return;
                }


            }
        }
        if(!found){
            System.out.println("Device not found.");
            return;
        }
    }
    /**
     * Updates the details of a device in the inventory, Time complexity: O(5*n) = O(n), n is number of devices.
     * @param name The name of the device to be updated.
     */
    public void updateDevice(String name) {
        int i = 0;
        boolean found = false; // search device
        boolean noPrice = false; // for leaving "enter new price" part blank
        boolean noQuantity = false; // for leaving "enter new quantity" part blank
        for (; i < 5; i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (Objects.equals(devices.get(i).get(j).getName(), name)) {
                    found = true;
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Enter new price (leave blank to keep current price):");
                    String priceInput = scanner.nextLine();
                    double price;
                    if (priceInput.trim().isEmpty()) {
                        // Treat empty input as zero
                        price = 0;
                        noPrice = true;
                    } else {
                        // Parse the input as a double
                        price = Double.parseDouble(priceInput);
                    }

                    System.out.println("Enter new quantity (leave blank to keep current quantity):");
                    String quantityInput = scanner.nextLine();
                    int quantity;
                    if (quantityInput.trim().isEmpty()) {
                        // Treat empty input as zero
                        quantity = 0;
                        noQuantity = true;
                    } else {
                        // Parse the input as an integer
                        quantity = Integer.parseInt(quantityInput);
                    }
                    if(!noPrice)
                        if(price >= 0) {
                            devices.get(i).get(j).setPrice(price);
                            System.out.println("Price updated.");
                        }
                            else System.out.println("Price cannot be negative.");
                    if(!noQuantity)
                        if(quantity >= 0) {
                            devices.get(i).get(j).setQuantity(quantity);
                            System.out.println("Quantity updated.");
                        }
                        else System.out.println("Quantity cannot be negative.");

                    return;
                }
            }
        }
        if (!found) {
            System.out.println("Device not found.");
        }
    }

    /**
     * Prints all devices stored in inventory by iterating through lists, Time complexity: O(n*m) = O(n), n is number of devices and m is number of categories.
     */
    public void printAllDevices() {
        int count = 1;
        System.out.println("Device List:");
        for (ArrayList<Device> deviceList : devices) {
            for (Device device : deviceList) {
                System.out.println((count++) + ". Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
                // You can print other details of the device as well
            }
        }
    }

    /**
     * Finds cheapest device by creating an iterator and searches for smallest price value, Time complexity: O(n*m) = O(n), n is number of devices and m is number of categories.
     */
    public void findCheapestDevice() {
        double minPrice = Double.MAX_VALUE;
        Device cheapestDevice = null;

        for (ArrayList<Device> deviceList : devices) {
            for (Device device : deviceList) {
                if (device.getPrice() < minPrice) {
                    minPrice = device.getPrice();
                    cheapestDevice = device;
                }
            }
        }
        System.out.print("The cheapest device is:\n" +
                "Category:" + cheapestDevice.getCategory() + " Name:" +  cheapestDevice.getName() + " Price:" + cheapestDevice.getPrice() + " Quantity:" + cheapestDevice.getQuantity() + "\n");
        return;
    }

    /**
     * Updates the details of a device in the inventory, Time complexity: O(n log n), n is number of devices.
     */
    public void sortAllDevicesByPrice() {
        ArrayList<Device> allDevices = new ArrayList<>();

        // Add all devices to the single ArrayList
        for (ArrayList<Device> deviceList : devices) {
            allDevices.addAll(deviceList);
        }

        // Sort all devices based on price
        Collections.sort(allDevices, new Comparator<Device>() {
            @Override
            public int compare(Device device1, Device device2) {
                // Compare the prices of the devices
                return Double.compare(device1.getPrice(), device2.getPrice());
            }
        });

        // Print sorted devices
        for (Device device : allDevices) {
            System.out.println("Category:" + device.getCategory() + " Name:" +  device.getName() + " Price:" + device.getPrice() + "$ Quantity:" + device.getQuantity() + "\n");
            // You can print other details of the device as well
        }
    }

    /**
     * Sums up all prices of devices by iteration method, Time complexity: O(m*n) = O(n), n is number of devices and m is number of categories.
     * @return Final value of summation.
     */
    public double calculateTotalPrice(){
        double totalPrice = 0;
        for(ArrayList<Device> myTempArrayList : devices){
            for(Device myTempDevice: myTempArrayList){
                totalPrice += myTempDevice.getPrice() * myTempDevice.getQuantity();
            }
        }
        return totalPrice;
    }
    /**
     * Searches for user-provided name and manipulates its quantity, Time complexity: O(5*n) = O(n), n is number of devices.
     * @param name The name of the device to be restocked.
     */
    public void restockDevice(String name) {
        int i = 0;
        boolean found = false;
        for(; i < 5; i++){
            for(int j = 0; j < devices.get(i).size(); j++) {
                if (Objects.equals(devices.get(i).get(j).getName(), name)) {
                    found = true;
                    Scanner scanner = new Scanner(System.in);
                    int currentQuantity = devices.get(i).get(j).getQuantity(); // store current value of quantity

                    System.out.println("Do you want to add or remove stock? (Add/Remove):");
                    String select = scanner.nextLine();


                    if(select.equals("Add")) {
                        System.out.println("Enter the quantity to add: ");
                        int newQuantity = scanner.nextInt();
                        devices.get(i).get(j).setQuantity(currentQuantity + newQuantity);
                        System.out.println(name + " restocked. New quantity: " + devices.get(i).get(j).getQuantity());

                    } else if (select.equals("Remove")) {
                        System.out.println("Enter the quantity to remove: ");
                        int newQuantity = scanner.nextInt();
                        if(currentQuantity - newQuantity >= 0) {
                            devices.get(i).get(j).setQuantity(currentQuantity - newQuantity);
                            System.out.println(name + " reduced. New quantity: " + devices.get(i).get(j).getQuantity());
                        }
                        else{
                            System.out.println("Quantity cannot be negative.");
                        }
                    }
                    else {
                        System.out.println("Unknown input.");

                    }

                return;

                }


            }
        }
        if(!found){
            System.out.println("Device not found.");
            return;
        }
    }
    /**
     * Exports a report of the inventory to the filename, Time complexity: O(m*n) = O(n), n is number of devices and m is number of categories.
     */
    public void exportReportToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            int amount = 1;
            writer.write("Electronics Shop Inventory Report\n");
            writer.write("Generated on: " + LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getMonth() + " " + LocalDateTime.now().getYear() + "\n");
            writer.write("--------------------------------------------\n");
            writer.write("| No. | Category | Name | Price | Quantity |\n");
            writer.write("--------------------------------------------\n");
            for (ArrayList<Device> deviceList : devices) {
                for (Device device : deviceList) {
                    writer.write("| " + (amount++) +" | " + device.getCategory() + " | " + device.getName() + " | $" + device.getPrice() + " | " + device.getQuantity() + " |\n");
                }
            }
            writer.write("--------------------------------------------\n");
            writer.write("Summary:\n");
            writer.write("Total number of devices: " + (amount - 1) + "\n");
            writer.write("Total inventory value: " + calculateTotalPrice() + "\n");
            writer.write("End of report\n");
            System.out.println("Report has been successfully exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
        }
    }
}

