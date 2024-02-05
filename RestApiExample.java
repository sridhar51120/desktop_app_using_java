import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApiExample {

    public static void main(String[] args) {
        // Example API endpoint (replace with your own)
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";

        // Call the function to get data from the API
        String apiData = getDataFromApi(apiUrl);

        // Display the retrieved data
        if (apiData != null) {
            System.out.println("Retrieved Data:");
            System.out.println(apiData);
        }
    }

    private static String getDataFromApi(String apiUrl) {
        try {
            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create an HttpRequest to the API endpoint
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            // Send the request and receive the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request was successful (status code 200)
            if (response.statusCode() == 200) {
                // Return the response body (JSON data)
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
