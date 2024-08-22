import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PerformanceChart extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Performance Analysis");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Nodes");
        yAxis.setLabel("Time (μs)");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("AVL Tree Operations Performance");

        XYChart.Series<Number, Number> addSeries = new XYChart.Series<>();
        addSeries.setName("ADD Operation");

        XYChart.Series<Number, Number> removeSeries = new XYChart.Series<>();
        removeSeries.setName("REMOVE Operation");

        XYChart.Series<Number, Number> searchSeries = new XYChart.Series<>();
        searchSeries.setName("SEARCH Operation");

        XYChart.Series<Number, Number> updateSeries = new XYChart.Series<>();
        updateSeries.setName("UPDATE Operation");
        // Add example data
        boolean firstAddSkipped = false;
        for (PerformanceData data : PerformanceData.getData("ADD")) {
            if (!firstAddSkipped) {
                firstAddSkipped = true;
                continue; // Skip the first ADD operation
            }

                addSeries.getData().add(new XYChart.Data<>(data.getNodes(), data.getTime()));
        }
        for (PerformanceData data : PerformanceData.getData("REMOVE")) {
            removeSeries.getData().add(new XYChart.Data<>(data.getNodes(), data.getTime()));
        }
        for (PerformanceData data : PerformanceData.getData("SEARCH")) {
            searchSeries.getData().add(new XYChart.Data<>(data.getNodes(), data.getTime()));
        }
        for (PerformanceData data : PerformanceData.getData("UPDATE")) {
            updateSeries.getData().add(new XYChart.Data<>(data.getNodes(), data.getTime()));
        }

        lineChart.getData().addAll(addSeries, removeSeries, searchSeries, updateSeries);

        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
