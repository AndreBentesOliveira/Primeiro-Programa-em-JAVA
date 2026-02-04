import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class ApiConnect {
    static String url = "https://v6.exchangerate-api.com/v6/268972ea18fe0a79e2aa0ee2/";
    static String builtUrl = "";
    static String apiResponse;
    static String apiLastUpdate;

    public static void urlBuild(String urlComplement){
        builtUrl = "";
        builtUrl = url + urlComplement;
        System.out.println(builtUrl);
    }

    public static void ApiConnect(){
        apiResponse = "";
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(builtUrl))
                .header("Authorization", "268972ea18fe0a79e2aa0ee2") // Set request headers
                .GET()
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Status Code: " + response.statusCode());
            //System.out.println("Response Body: " + response.body());
            //apiResponse = response.body();
            String jsonData = response.body();
            Gson gson = new Gson();
            Response responseObj = gson.fromJson(jsonData, Response.class);
            apiResponse = responseObj.getConversionResult();
            apiLastUpdate = responseObj.getLastUpdate();
            System.out.println(apiResponse);
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

    }
}

class ReadFile{
    public static String[] getFileContent() {
        ArrayList<String> contrys = new ArrayList<>();
        String fileName = "C:\\Users\\andre.oliveira\\IdeaProjects\\conversor_de_moedas\\src\\main\\java\\contrys.txt";
        // Use try-with-resources to ensure the reader is closed automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process the line (e.g., print it, store it in a list)
                //System.out.println(line);
                contrys.add(line);
            }
        } catch (IOException e) {
            // Handle exceptions like file not found or read errors
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        String[] stringList = contrys.toArray(new String[0]);
        return stringList;
    }
}

class Response{
    private String result;
    private String documentation;
    private String terms_of_use;
    private int time_last_update_unix;
    private String time_last_update_utc;
    private int time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private String target_code;
    private double conversion_rate;
    private String conversion_result;

    public String getConversionResult() { return conversion_result; }
    public String getLastUpdate(){return time_last_update_utc;}
}