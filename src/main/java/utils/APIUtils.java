package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import models.PostModelForResponse;
import models.Posts;
import models.User;
import models.Users;

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

    public static void postRequest(String request, String body) {
        MyLogger.logInfo("Post request");
        response = given().body(body).when().post(request);
    }

    public static int getStatusCode() {
        MyLogger.logInfo("Get status code");
        return response.getStatusCode();
    }

    public static boolean isBodyJsonArrayFormat() {
        MyLogger.logInfo("Checking response body for JSON array type");
        return JsonArray.class.equals(GSON.fromJson(response.getBody().asString(), JsonArray.class).getClass());
    }

    public static JsonObject getBodyFromJsonObject() {
        MyLogger.logInfo("Response body converting in JSON Object");
        return GSON.fromJson(response.getBody().asString(), JsonObject.class);
    }

    public static boolean isAscendingIdOrder() {
        MyLogger.logInfo("Checking response body for ascending id order");

        Posts posts = new Posts();
        Type postsListType = new TypeToken<ArrayList<PostModelForResponse>>() {}.getType();
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

    public static Object getObjectValue(String key) {
        MyLogger.logInfo("Get response body value by key = '" + key + "'");
        PostModelForResponse post = GSON.fromJson(response.getBody().asString(), PostModelForResponse.class);
        return post.getValue(key);
    }

    public static String setBodyToJson(Object object) {
        MyLogger.logInfo("Response body converting in String value");
        return new Gson().toJson(object);
    }

    public static User getUserFromArrayById(int id) {
        MyLogger.logInfo("Get object from array by id");
        Users users = new Users();

        Type usersListType = new TypeToken<ArrayList<User>>() {}.getType();
        users.setUsers(GSON.fromJson(response.getBody().asString(), usersListType));

        return users.getUserById(id);
    }

    public static User getUserFromResponseBody() {
        MyLogger.logInfo("Get object from response body");
        return GSON.fromJson(response.getBody().asString(), User.class);
    }
}
