package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIUtils {

    private static final String URL = ConfigManager.getTestDataString("postsURL");

    public static void main(String[] args) {
        requestGet();
    }

    private static void requestGet() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIUtils::parse)
                .join();
    }

    private static String parse(String responseBody) {
        JSONArray users = new JSONArray(responseBody);

        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            int id = user.getInt("id");
            int userId = user.getInt("userId");
            String title = user.getString("title");
            String body = user.getString("body");

            System.out.println(id + " " + title + " " + userId + " " + body);
        }

        return null;
    }
}
