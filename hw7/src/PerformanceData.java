import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PerformanceData {
    private static final Map<String, List<PerformanceData>> dataMap = new HashMap<>();

    private final String operation;
    private final int nodes;
    private final double time;

    public PerformanceData(String operation, int nodes, double time) {
        this.operation = operation;
        this.nodes = nodes;
        this.time = time;
    }

    public String getOperation() {
        return operation;
    }

    public int getNodes() {
        return nodes;
    }

    public double getTime() {
        return time;
    }

    public static void addData(String operation, int nodes, double time) {
        dataMap.computeIfAbsent(operation, k -> new ArrayList<>()).add(new PerformanceData(operation, nodes, time));
    }

    public static List<PerformanceData> getData(String operation) {
        return dataMap.getOrDefault(operation, new ArrayList<>());
    }

    public static void clearData() {
        dataMap.clear();
    }
}
