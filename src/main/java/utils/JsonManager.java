package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JsonManager {

    private static Gson gson;

    private JsonManager() {
        gson = new Gson();
    }

    private static Gson getGson() {
        if (gson == null)
            new JsonManager();
        return gson;
    }

    public static <T> T getObject(JsonReader jsonReader, Class<T> cls) {
        SmartLogger.logInfo("Converting jsonReader to Object");
        return getGson().fromJson(jsonReader, cls);
    }

    public static <T> T getObject(String jsonString, Class<T> cls) {
        SmartLogger.logInfo("Converting jsonString to Object");
        return getGson().fromJson(jsonString, cls);
    }
}
