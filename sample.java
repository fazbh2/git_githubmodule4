import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
public class WebsiteEndpointExtractor {
    public static void main(String[] args) {
        String jsonUrl = "https://example.com/api-endpoints"; // Replace with the JSON URL of the website
        try {
            String jsonString = fetchJsonString(jsonUrl);
            if (jsonString != null) {
                extractEndpoints(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String fetchJsonString(String jsonUrl) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        URL url = new URL(jsonUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        }
        return jsonString.toString();
    }
    private static void extractEndpoints(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        // Assuming the JSON structure contains an array of endpoints under "endpoints" key
        JSONArray endpointsArray = json.getJSONArray("endpoints");
        System.out.println("Website Endpoints:");
        for (int i = 0; i < endpointsArray.length(); i++) {
            String endpoint = endpointsArray.getString(i);
            System.out.println(endpoint);
        }
    }
}

