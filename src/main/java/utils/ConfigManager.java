package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Properties;

public class ConfigManager {

    private static final String TEST_DATA_PATH = "src/test/resources/testData.properties";

    public static String getTestDataString(String key) {
        return getProperties(TEST_DATA_PATH).getProperty(key);
    }

    public static int getTestDataInt(String key) {
        return Integer.parseInt(getProperties(TEST_DATA_PATH).getProperty(key));
    }

    private static Properties getProperties(String path) {
        try (FileInputStream fileTestDataInputStream = new FileInputStream(path)) {
            Properties testDataProperties = new Properties();
            testDataProperties.load(fileTestDataInputStream);

            return testDataProperties;
        } catch (IOException e) {
            MyLogger.logError("Error - don't reading config file");
            e.printStackTrace();
        }

        return null;
    }

    public static void saveData(String path, String object) {
        MyLogger.logInfo("Save data in file " + path);

        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(object);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readData(String path, Type type) {
        MyLogger.logInfo("Read data in file " + path);

        try (JsonReader reader = new JsonReader(new FileReader(path))) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteFile(String path) {
        MyLogger.logInfo("Delete file " + path);
        new File(path).delete();
    }
}
