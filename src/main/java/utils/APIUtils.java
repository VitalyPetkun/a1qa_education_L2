package utils;

import com.google.gson.Gson;
import io.restassured.response.Response;
import model.Post;
import static io.restassured.RestAssured.*;

public class APIUtils {

    private static final String BASE_URL = ConfigManager.getTestDataString("baseURL");

    private static final Gson GSON = new Gson();

    private static Response response;

    private static void setupBaseUri() {
        baseURI = BASE_URL;
    }

    public static void requestGet(String request) {
        setupBaseUri();
        response = get(request);
    }

    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static String getBodyFromJson() {
        return GSON.toJson(response.getBody().asString());
    }

    public static Object getValue(String key) {
        Post post = GSON.fromJson(response.getBody().asString(), Post.class);
        return post.getValue(key);
    }
}
