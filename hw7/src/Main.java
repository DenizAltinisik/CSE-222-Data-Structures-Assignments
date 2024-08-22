import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestDataGenerator.generateTestFile("input.txt",1000 , 4000, 0, 0, 0);

        measurePerformance("input.txt", 1000);
    }

    public static void measurePerformance(String filename, int initialNodes) {
        PerformanceData.clearData(); // Clear previous data
        StockDataManager manager = new StockDataManager(); // avl tree
        int nodeCount = 0;
        nodeCount += initialNodes;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            long startTime, endTime1, endTime2, endTime3, endTime4;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String command = parts[0];

                switch (command) {
                    case "ADD":
                        startTime = System.nanoTime();
                        manager.addStock(parts[1], Double.parseDouble(parts[2].replace(",", ".")), Long.parseLong(parts[3]), Long.parseLong(parts[4]));
                        endTime1 = System.nanoTime();
                        nodeCount++;
                        PerformanceData.addData("ADD", nodeCount, (endTime1 - startTime)); // Time in microseconds
                        System.out.println("Add: " + nodeCount + "= " + (endTime1 - startTime));
                        break;
                    case "REMOVE":
                        startTime = System.nanoTime();
                        manager.removeStock(parts[1]);
                        endTime2 = System.nanoTime();
                        nodeCount = Math.max(nodeCount - 1, 0);
                        PerformanceData.addData("REMOVE", nodeCount, (endTime2 - startTime)); // Time in microseconds
                        break;
                    case "SEARCH":
                        startTime = System.nanoTime();
                        manager.searchStock(parts[1]);
                        endTime3 = System.nanoTime();
                        PerformanceData.addData("SEARCH", nodeCount, (endTime3 - startTime)); // Time in microseconds
                        break;
                    case "UPDATE":
                        startTime = System.nanoTime();
                        manager.updateStock(parts[1], parts[2], Double.parseDouble(parts[3].replace(",", ".")), Long.parseLong(parts[4]), Long.parseLong(parts[5]));
                        endTime4 = System.nanoTime();
                        PerformanceData.addData("UPDATE", nodeCount, (endTime4 - startTime)); // Time in microseconds
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Launch the GUI
        PerformanceChart.launch(PerformanceChart.class);
    }
}
