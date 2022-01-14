package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_PATH = "src/main/resources/config.properties";
    private static final String TEST_DATA_PATH = "src/test/java/resources/testData.properties";

    private static Properties configProperties;
    private static Properties testDataProperties;

    static {
        try (FileInputStream fileConfigInputStream = new FileInputStream(CONFIG_PATH);
             FileInputStream fileTestDataInputStream = new FileInputStream(TEST_DATA_PATH)) {
            configProperties = new Properties();
            configProperties.load(fileConfigInputStream);
            testDataProperties = new Properties();
            testDataProperties.load(fileTestDataInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTestDataString(String key) {
        return testDataProperties.getProperty(key);
    }

    public static long getTestDataLong(String key) { return Long.parseLong(testDataProperties.getProperty(key));}

    public static int getConfigInt(String key) {
        return Integer.parseInt(configProperties.getProperty(key));
    }
}