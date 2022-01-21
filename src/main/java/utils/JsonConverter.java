package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import models.User;
import models.post.PostModelForResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    private static final Gson GSON = new Gson();

    public static Object convertToJson(String value, Type type) {
        Logger.logInfo("Converting \n" + value + "\n  to " + type.toString());
        return GSON.fromJson(value, type);
    }

    public static Object convertToJson(JsonReader reader, Type type) {
        Logger.logInfo("Converting \n" + reader.toString() + "\n to " + type.toString());
        return GSON.fromJson(reader, type);
    }

    public static List<PostModelForResponse> convertToPostList(String value) {
        Logger.logInfo("Converting \n" + value + "\n to List<PostModelForRequest>");
        Type listType = new TypeToken<ArrayList<PostModelForResponse>>() {}.getType();
        return GSON.fromJson(value, listType);
    }

    public static List<User> convertToUserList(String value) {
        Logger.logInfo("Converting \n" + value + "\n to List<User>");
        Type listType = new TypeToken<ArrayList<User>>() {}.getType();
        return GSON.fromJson(value, listType);
    }

    public static String convertFromJson(Object object) {
        Logger.logInfo("Converting " + object.toString() + " to String");
        return GSON.toJson(object);
    }
}
