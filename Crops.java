package com.example.java1;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Crops extends Application {

    private Stage stage;
    private Scene cropScene, pieChartScene;
    private TableView<CropData> tableView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        Image icon = new Image(getClass().getResourceAsStream("/com/example/java1/cropimage.png"));
        // Set application icon
        stage.getIcons().add(icon);

        // Connect to MySQL database (replace with your database credentials)
        String url = "jdbc:mysql://localhost:3306/Crops";
        String user = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, user, password);

        // Set up TableView for crop data
        tableView = createTableView();

        // Set up PieChart for crop production quantities
        PieChart pieChart = createPieChart(conn);

        // Button to switch to PieChart scene
        Button switchToPieChartButton = new Button("Switch to Pie Chart");
        switchToPieChartButton.setOnAction(e -> stage.setScene(pieChartScene));

        // Button to switch back to TableView scene
        Button switchToTableViewButton = new Button("Back to Table View");
        switchToTableViewButton.setOnAction(e -> stage.setScene(cropScene));

        // Layout for TableView scene
        VBox cropLayout = new VBox(10);
        cropLayout.getChildren().addAll(tableView, switchToPieChartButton);
        cropScene = new Scene(cropLayout, 800, 600);

        // Layout for PieChart scene
        VBox pieChartLayout = new VBox(10);
        pieChartLayout.getChildren().addAll(pieChart, switchToTableViewButton);
        pieChartScene = new Scene(pieChartLayout, 800, 600);

        // Close database connection
        conn.close();

        // Set initial scene
        stage.setScene(cropScene);
        stage.setTitle("Crop Production Information");
        stage.show();
    }

    private TableView<CropData> createTableView() throws Exception {
        TableView<CropData> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Define columns
        TableColumn<CropData, String> cropColumn = new TableColumn<>("Crop");
        TableColumn<CropData, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<CropData, Double> productionColumn = new TableColumn<>("Production Quantity (tons)");

        cropColumn.setCellValueFactory(new PropertyValueFactory<>("crop"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        productionColumn.setCellValueFactory(new PropertyValueFactory<>("production"));

        tableView.getColumns().addAll(cropColumn, descriptionColumn, productionColumn);

        // Query data from database
        String query = "SELECT crop_name AS crop, description AS description, production_quantity AS production " +
                "FROM crop_production";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crops", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        // Populate table with data
        List<CropData> cropList = new ArrayList<>();
        while (resultSet.next()) {
            String crop = resultSet.getString("crop");
            String description = resultSet.getString("description");
            double production = resultSet.getDouble("production");
            CropData cropData = new CropData(crop, description, production);
            cropList.add(cropData);
        }

        tableView.getItems().addAll(cropList);

        // Close database connection
        resultSet.close();
        stmt.close();
        conn.close();

        return tableView;
    }

    private PieChart createPieChart(Connection conn) throws Exception {
        // Query data from database
        String query = "SELECT crop_name AS crop, production_quantity AS production FROM crop_production";

        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        // Prepare data for pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String crop = resultSet.getString("crop");
            double production = resultSet.getDouble("production");
            pieChartData.add(new PieChart.Data(crop, production));
        }

        // Close database connection
        resultSet.close();
        stmt.close();

        // Create PieChart
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Crop Production Quantities Pie Chart");

        return pieChart;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // CropData class for TableView
    public static class CropData {
        private final String crop;
        private final String description;
        private final double production;

        public CropData(String crop, String description, double production) {
            this.crop = crop;
            this.description = description;
            this.production = production;
        }

        public String getCrop() {
            return crop;
        }

        public String getDescription() {
            return description;
        }

        public double getProduction() {
            return production;
        }
    }
}
