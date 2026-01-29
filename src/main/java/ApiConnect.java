import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class ApiConnect {
    static String url = "https://v6.exchangerate-api.com/v6/268972ea18fe0a79e2aa0ee2/";
    static String builtUrl = "";
    static String apiResponse;

    public static void urlBuild(String urlComplement){
        builtUrl = "";
        builtUrl = url + urlComplement;
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
                .GET() // Specify the HTTP method
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            //System.out.println("Response Body: " + response.body());
            apiResponse = response.body();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

    }
}
