package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import model.Post;
import model.Posts;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class APIUtils {

    private static final Gson GSON = new Gson();

    private static Response response;

    public static void setupBaseUri(String BASE_URI) {
        MyLogger.logInfo("Setup baseUri");
        baseURI = BASE_URI;
    }

    public static void getRequest(String request) {
        MyLogger.logInfo("Get request");
        response = get(request);
    }

    public static int getStatusCode() {
        MyLogger.logInfo("Get status code");
        return response.getStatusCode();
    }

    public static JsonObject getBodyFromJsonObject() {
        MyLogger.logInfo("Response body converting in JSON Object");
        return GSON.fromJson(response.getBody().asString(), JsonObject.class);
    }

    public static boolean isBodyJsonArrayFormat() {
        MyLogger.logInfo("Checking response body for JSON array type");
        return JsonArray.class.equals(GSON.fromJson(response.getBody().asString(), JsonArray.class).getClass());
    }

    public static boolean isAscendingIdOrder() {
        MyLogger.logInfo("Checking response body for ascending id order");

        Posts posts = new Posts();
        Type postsListType = new TypeToken<ArrayList<Post>>() {}.getType();
        posts.setPosts(GSON.fromJson(response.getBody().asString(), postsListType));

        for (int i = 0; i < posts.getPosts().size(); i++) {
            if (i != posts.getPosts().size() - 1) {
                if (!(posts.getPosts().get(i).getId() < posts.getPosts().get(i + 1).getId()))
                    return false;
            } else {
                return true;
            }
        }

        return false;
    }

    public static Object getValue(String key) {
        MyLogger.logInfo("Get response body value by key = '" + key + "'");
        Post post = GSON.fromJson(response.getBody().asString(), Post.class);
        return post.getValue(key);
    }
}
