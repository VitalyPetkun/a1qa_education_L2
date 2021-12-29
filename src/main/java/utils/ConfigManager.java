package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    protected static FileInputStream fileConfigInputStream;
    protected static FileInputStream fileTestDataInputStream;
    protected static Properties configProperties;
    protected static Properties testDataProperties;
    static {
        try {
            fileConfigInputStream = new FileInputStream("src/main/resources/config.properties");
            configProperties = new Properties();
            configProperties.load(fileConfigInputStream);
            fileTestDataInputStream = new FileInputStream("src/test/java/resources/testData.properties");
            testDataProperties = new Properties();
            testDataProperties.load(fileTestDataInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileConfigInputStream != null)
                try {
                    fileConfigInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); }
            if (fileTestDataInputStream != null)
                try {
                    fileTestDataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); }
        }
    }

    public static String getTestDataString(String key) {
        return testDataProperties.getProperty(key);
    }

    public static int getConfigInt(String key) {
        return Integer.parseInt(configProperties.getProperty(key));
    }
}