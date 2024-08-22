import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {
    public static void generateTestFile(String filename, int initialNodes, int addOps, int removeOps, int searchOps, int updateOps) {
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();
            List<String> symbols = new ArrayList<>();
            List<String> commands = new ArrayList<>();
            List<String> initCommands = new ArrayList<>();
            // Generate initial ADD commands
            for (int i = 0; i < initialNodes; i++) {
                String symbol = generateRandomSymbol(random);
                double price = random.nextDouble() * 1000;
                long volume = random.nextInt(1000000);
                long marketCap = random.nextInt(10000000);
                symbols.add(symbol);
                initCommands.add(String.format("ADD %s %.2f %d %d", symbol, price, volume, marketCap));
            }

            // Generate ADD operations
            for (int i = 0; i < addOps; i++) {
                String symbol = generateRandomSymbol(random);
                double price = random.nextDouble() * 1000;
                long volume = random.nextInt(1000000);
                long marketCap = random.nextInt(10000000);
                symbols.add(symbol);
                commands.add(String.format("ADD %s %.2f %d %d", symbol, price, volume, marketCap));
            }

            // Generate REMOVE operations
            for (int i = 0; i < removeOps; i++) {
                if (!symbols.isEmpty()) {
                    String symbol = symbols.remove(random.nextInt(symbols.size()));
                    commands.add(String.format("REMOVE %s", symbol));
                }
            }

            // Generate SEARCH operations
            for (int i = 0; i < searchOps; i++) {
                if (!symbols.isEmpty()) {
                    String symbol = symbols.get(random.nextInt(symbols.size()));
                    commands.add(String.format("SEARCH %s", symbol));
                }
            }

            // Generate UPDATE operations
            for (int i = 0; i < updateOps; i++) {
                if (!symbols.isEmpty()) {
                    String symbol = symbols.get(random.nextInt(symbols.size()));
                    String newSymbol = generateRandomSymbol(random);
                    double price = random.nextDouble() * 1000;
                    long volume = random.nextInt(1000000);
                    long marketCap = random.nextInt(10000000);
                    commands.add(String.format("UPDATE %s %s %.2f %d %d", symbol, newSymbol, price, volume, marketCap));
                    symbols.remove(symbol);
                    symbols.add(newSymbol);
                }
            }

            // Shuffle commands to mix them
            java.util.Collections.shuffle(commands);

            for(String command : initCommands) {
                writer.write(command + "\n");

            }
            // Write commands to file
            for (String command : commands) {
                writer.write(command + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomSymbol(Random random) {
        return random.ints(65, 91)
                .limit(4)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
