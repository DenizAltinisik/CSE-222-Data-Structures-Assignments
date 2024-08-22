import java.time.LocalDateTime;
import java.util.*;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.sound.sampled.*;
public class Main {
    private static Clip clip;
    public static void main(String[] args) {
        playSound("field_of_hopes_and_dreams.wav");
        Inventory inventory = new Inventory();
        TV tv1 = new TV("LG OLED55", 800, 30);
        Laptop laptop1 = new Laptop("Dell XPS 13", 1200, 50);
        Headphone hp1 = new Headphone("Sony WH-1000XM4", 250, 100);
        Smartphone sp1 = new Smartphone("Samsung S21 Black", 500, 250);
        SmartWatch sw1 = new SmartWatch("Apple Watch Series", 399, 75);

        inventory.addDevice(tv1);
        inventory.addDevice(laptop1);
        inventory.addDevice(hp1);
        inventory.addDevice(sp1);
        inventory.addDevice(sw1);
        while (true){
            System.out.println("\n\n\nWelcome to the Electronics Inventory Management System!\n" +
                    "Please select an option:\n" +
                    "1. Add a new device\n" +
                    "2. Remove a device\n" +
                    "3. Update device details\n" +
                    "4. List all devices\n" +
                    "5. Find the cheapest device\n" +
                    "6. Sort devices by price\n" +
                    "7. Calculate total inventory value\n" +
                    "8. Restock a device\n" +
                    "9. Export inventory report\n" +
                    "(10) Pause the music\n" +
                    "(11) Continue to music\n" +
                    "0. Exit");
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        String category = null;
        String name = null;
        double price = 0;
        int quantity = 0;
            switch (selection) {
                case 1:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Enter category name: ");
                    category = scanner1.nextLine();
                    System.out.println("Enter device name:");
                    name = scanner1.nextLine();
                    System.out.println("Enter price:");
                    price = scanner1.nextDouble();
                    System.out.println("Enter quantity:");
                    quantity = scanner1.nextInt();
                    if (category.equals("Smart Phone")) {
                        Smartphone temp = new Smartphone(name, price, quantity);
                        inventory.addDevice(temp);
                        System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added.");
                    } else if (category.equals("TV")) {
                        TV temp = new TV(name, price, quantity);

                        inventory.addDevice(temp);
                        System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added.");
                    } else if (category.equals("Laptop")) {
                        Laptop temp = new Laptop(name, price, quantity);

                        inventory.addDevice(temp);
                        System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added.");
                    } else if (category.equals("Smart Watch")) {
                        SmartWatch temp = new SmartWatch(name, price, quantity);

                        inventory.addDevice(temp);
                        System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added.");
                    } else if (category.equals("Headphones")) {
                        Headphone temp = new Headphone(name, price, quantity);

                        inventory.addDevice(temp);
                        System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added.");
                    } else {
                        System.out.println("Unknown category.");
                    }
                    break;
                case 2:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Enter the name of device to remove: ");
                    name = scanner2.nextLine();
                    inventory.removeDevice(name);
                    break;
                case 3:
                    System.out.print("Enter the name of the device to update: ");
                    Scanner scanner3 = new Scanner(System.in);
                    name = scanner3.nextLine();
                    inventory.updateDevice(name);
                    break;
                case 4:
                    inventory.printAllDevices();
                    break;
                case 5:
                    inventory.findCheapestDevice();
                    break;
                case 6:
                    System.out.println("Devices sorted by price:");
                    inventory.sortAllDevicesByPrice();
                    break;
                case 7:
                    System.out.print("Total inventory value: $" + inventory.calculateTotalPrice());

                    break;
                case 8:
                    System.out.print("Enter the name of the device to restock:");
                    Scanner scanner4 = new Scanner(System.in);
                    name = scanner4.nextLine();
                    inventory.restockDevice(name);
                    break;
                case 9:
                    inventory.exportReportToFile("report.txt");
                    break;
                case 10:
                    pauseSound();
                    break;
                case 11:
                    resumeSound();
                    break;
                case 0:
                    System.out.println("Program finished.");
                    return;
                default:
            }
        }
    }
    public static void playSound(String soundFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(soundFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void pauseSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void resumeSound() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
}