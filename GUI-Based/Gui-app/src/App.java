import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField linkField = new TextField();
        Button submitButton = new Button("Submit");
        Label outputLabel = new Label();
        submitButton.setOnAction(event -> {
            String apiUrl = linkField.getText();
            String apiData = getDataFromApi(apiUrl);

            if (apiData != null) {
                outputLabel.setText("Retrieved Data:\n" + apiData);
            } else {
                outputLabel.setText("Error retrieving data.");
            }

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(outputLabel);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            Stage scrollStage = new Stage();
            scrollStage.setTitle("Output");
            scrollStage.setScene(new Scene(scrollPane, 400, 300));
            scrollStage.show();
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(linkField, submitButton, outputLabel);

        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setTitle("REST API Pane");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private static String getDataFromApi(String apiUrl) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Error: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
