package utils;

import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Properties;

public class ConfigManager {

    private ConfigManager() {
    }

    private static final String TEST_DATA_PATH = "src/test/resources/testData.properties";

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

    public static void saveData(String path, String object) {
        SmartLogger.logInfo("Save data in file");
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(object);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readData(String path, Type type) {
        SmartLogger.logInfo("Read data in file");
        try (JsonReader reader = new JsonReader(new FileReader(path))) {
            return JsonConverter.convertToJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteFile(String path) {
        SmartLogger.logInfo("Delete file");
        new File(path).delete();
    }
}
