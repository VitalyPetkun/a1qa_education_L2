package utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_DATA_PATH = "src/main/resources/config.properties";

    private ConfigManager() {
    }

    public static String getConfigDataValue(String key) {
        return getProperties(CONFIG_DATA_PATH).getProperty(key);
    }

    private static Properties getProperties(String path) {
        try (FileInputStream fileTestDataInputStream = new FileInputStream(path)) {
            Properties testDataProperties = new Properties();
            testDataProperties.load(fileTestDataInputStream);

            return testDataProperties;
        } catch (IOException e) {
            SmartLogger.logError("Don't reading config file");
        }

        return null;
    }
}
