package utils.dataBase;

import utils.PropertiesManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static services.DataBaseConst.*;

public class DataBaseHandler {

    private static final String JDBC_DRIVER = PropertiesManager.getConfigValue("jdbcDriver");
    private static final String CONNECTION_URL = URL.getConst().concat(HOST.getConst()).concat(":").
            concat(PORT.getConst()).concat("/").concat(DATA_BASE_NAME.getConst());
    private static final String DB_USER = USER.getConst();
    private static final String DB_PASSWORD = PASSWORD.getConst();

    private static Connection dbConnection;

    private DataBaseHandler() {
    }

    public static Connection getDbConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            dbConnection = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
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
            e.printStackTrace();
        }
        return counter;
    }

    public static int randomId(int maxId, int minId) {
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
