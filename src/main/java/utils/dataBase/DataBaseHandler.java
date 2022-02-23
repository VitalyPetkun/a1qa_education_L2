package utils.dataBase;

import utils.PropertiesManager;
import utils.SmartLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static services.DataBaseConst.*;

public class DataBaseHandler {

    private static final String jdbcDriver = PropertiesManager.getConfigValue(DRIVER.getConst());
    private static final String url = PropertiesManager.getConfigValue(URL.getConst())
            .concat(PropertiesManager.getConfigValue(HOST.getConst()))
            .concat(":").concat(PropertiesManager.getConfigValue(PORT.getConst()))
            .concat("/").concat(PropertiesManager.getConfigValue(DATA_BASE_NAME.getConst()));
    private static final String user = PropertiesManager.getConfigValue(USER.getConst());
    private static final String password = PropertiesManager.getConfigValue(PASSWORD.getConst());

    private static Connection dbConnection;

    private DataBaseHandler() {
    }

    public static Connection dbConnection() {
        SmartLogger.logInfo("DataBase connection");
        try {
            Class.forName(jdbcDriver);
            dbConnection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            SmartLogger.logError("Error connection dataBase");
        }

        return dbConnection;
    }


    public static boolean isItem(ResultSet resultSet) {
        SmartLogger.logInfo("Checking resultSet for notNull");
        if (resultSetSize(resultSet) > 0)
            return true;

        return false;
    }

    public static int resultSetSize(ResultSet resultSet) {
        SmartLogger.logInfo("Get size resultSet");
        int counter = 0;
        try {
            while (resultSet.next()) {
                ++counter;
            }
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }
        return counter;
    }

    public static int getRandomId(int maxId, int minId) {
        SmartLogger.logInfo("Get random id");
        int number = (int) (Math.random() * (((maxId + 1) - minId) + 1)) + minId;
        char[] numberArray = String.valueOf(number).toCharArray();

        for (int i = 0; i < numberArray.length; i++) {
            for (int j = i + 1; j < numberArray.length; j++) {
                if (numberArray[i] == numberArray[j]) {
                    return number;
                }
            }
        }

        return getRandomId(maxId, minId);
    }
}
