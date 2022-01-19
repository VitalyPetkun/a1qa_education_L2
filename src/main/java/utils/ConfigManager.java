package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String TEST_DATA_PATH = "src/test/resources/testData.properties";

    private static Properties testDataProperties;

    static {
        try (FileInputStream fileTestDataInputStream = new FileInputStream(TEST_DATA_PATH)) {
            testDataProperties = new Properties();
            testDataProperties.load(fileTestDataInputStream);
        } catch (IOException e) {
            MyLogger.logError("Error - don't reading config file");
            e.printStackTrace();
        }
    }

    public static String getTestDataString(String key) {
        return testDataProperties.getProperty(key);
    }

    public static int getTestDataInt(String key) {
        return Integer.parseInt(testDataProperties.getProperty(key));
    }
}
