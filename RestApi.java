import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create controls
        TextField linkField = new TextField();
        Button submitButton = new Button("Submit");
        Label outputLabel = new Label();

        // Set up event handling for the submit button
        submitButton.setOnAction(event -> {
            String apiUrl = linkField.getText();
            String apiData = getDataFromApi(apiUrl);

            if (apiData != null) {
                outputLabel.setText("Retrieved Data:\n" + apiData);
            } else {
                outputLabel.setText("Error retrieving data.");
            }
        });

        // Create a VBox layout to arrange the controls vertically
        VBox vbox = new VBox(10); // 10 pixels spacing between controls
        vbox.setPadding(new Insets(20, 20, 20, 20)); // Padding around the VBox
        vbox.getChildren().addAll(linkField, submitButton, outputLabel);

        // Create a Scene with the VBox as the root
        Scene scene = new Scene(vbox, 400, 200);

        // Set the title of the stage
        primaryStage.setTitle("REST API Pane");

        // Set the scene for the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private String getDataFromApi(String apiUrl) {
        // Implement your logic to make an HTTP request and retrieve data
        // Replace this with your actual REST API data retrieval code

        // For demonstration purposes, returning a sample data
        return "Sample Data for " + apiUrl;
    }
}
