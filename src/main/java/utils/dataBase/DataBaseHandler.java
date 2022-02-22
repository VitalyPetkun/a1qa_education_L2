package utils.dataBase;

import services.DataBaseConst;
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

    public static Connection getDbConnection() {
        try {
            Class.forName(jdbcDriver);
            dbConnection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            SmartLogger.logError("Error connection dataBase");
        }

        return dbConnection;
    }


    public static boolean isItem(ResultSet resultSet) {
        if (resultSetSize(resultSet) > 0)
            return true;

        return false;
    }

    public static int resultSetSize(ResultSet resultSet) {
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

    public static int randomId(int maxId, int minId) {
        SmartLogger.logInfo("Get random id");

        int number = (int) (Math.random() * (((maxId + 1) - minId) + 1)) + minId;

        String strNumber = String.valueOf(number);
        char[] numberArray = strNumber.toCharArray();

        for (int i = 0; i < numberArray.length; i++) {
            for (int j = i + 1; j < numberArray.length; j++) {
                if (numberArray[i] == numberArray[j]) {
                    return number;
                }
            }
        }

        return randomId(maxId, minId);
    }
}
