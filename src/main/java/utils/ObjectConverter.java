package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import models.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectConverter {

    private static Gson gson;
    private static XmlMapper xmlMapper;

    private ObjectConverter() {
        gson = new Gson();
        xmlMapper = new XmlMapper();
    }

    private static Gson getGson() {
        if(gson == null)
            new ObjectConverter();
        return gson;
    }

    private static XmlMapper getXmlMapper() {
        if(xmlMapper == null)
            new ObjectConverter();
        return xmlMapper;
    }

    public static <T> T getObject(JsonReader jsonReader, Class<T> cls) {
        SmartLogger.logInfo("Converting jsonReader to Object");
        return getGson().fromJson(jsonReader, cls);
    }

    public static <T> T getObject(String jsonString, Class<T> cls) {
        SmartLogger.logInfo("Converting jsonString to Object");
        return getGson().fromJson(jsonString, cls);
    }

    public static <T> List<T> getListJson(String jsonString, Class<T> cls) {
        SmartLogger.logInfo("Converting jsonString to json List");
        List<T> list = new ArrayList<>();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();

        for (JsonElement jsonElement:jsonArray) {
            list.add(getObject(jsonElement.toString(), cls));
        }

        return list;
    }

    public static <T> List<T> getListXml(String xml, Class<T[]> cls) {
        SmartLogger.logInfo("Converting string to xml List");
        List<T> list = new ArrayList<>();
        T[] array = null;

        try {
            array = getXmlMapper().readValue(xml, cls);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for (T obj:array) {
            list.add(obj);
        }

        return list;
    }

    public static String getString(Object object) {
        SmartLogger.logInfo("Converting Object to String");
        return getGson().toJson(object);
    }
}
