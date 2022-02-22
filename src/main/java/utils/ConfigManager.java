package utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static final String TEST_DATA_PATH = "src/test/resources/testData.properties";

    private ConfigManager() {
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
}
