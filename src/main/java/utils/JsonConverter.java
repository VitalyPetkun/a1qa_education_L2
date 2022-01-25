package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;

public class JsonConverter {

    private static final Gson GSON = new Gson();

    private JsonConverter() {
    }

    public static Object convertToJson(String value, Type type) {
        SmartLogger.logInfo("Converting String to Json");
        return GSON.fromJson(value, type);
    }

    public static Object convertToJson(JsonReader reader, Type type) {
        SmartLogger.logInfo("Converting JsonReader to Json");
        return GSON.fromJson(reader, type);
    }

    public static String convertToString(Object object) {
        SmartLogger.logInfo("Converting Object to String");
        return GSON.toJson(object);
    }
}
