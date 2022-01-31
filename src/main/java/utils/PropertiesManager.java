package utils;

import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Properties;

public class PropertiesManager {

    private static final String TEST_DATA_PATH = "src/test/resources/testData.properties";

    private PropertiesManager() {
    }

    public static String getTestDataValue(String key) {
        return getProperties(TEST_DATA_PATH).getProperty(key);
    }

    private static Properties getProperties(String path) {
        try (FileInputStream fileTestDataInputStream = new FileInputStream(path)) {
            Properties testDataProperties = new Properties();
            testDataProperties.load(fileTestDataInputStream);

            return testDataProperties;
        } catch (IOException e) {
            SmartLogger.logError("Don't reading config file");
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T readData(String path, Class<T> cls) {
        SmartLogger.logInfo("Read data in file");
        try (JsonReader reader = new JsonReader(new FileReader(path))) {
            return JsonManager.getObject(reader, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
