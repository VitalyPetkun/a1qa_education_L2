package utils.dataBases.union_Reporting;

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

    private static int counter = 0;

    private DataBaseHandler() {}

    public static Connection getDbConnection() {
        try{
            Class.forName(JDBC_DRIVER);
            dbConnection = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
        } catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

        return dbConnection;
    }


    public static boolean isItem(ResultSet resultSet) {
        counter = 0;
        try {
            while (resultSet.next()) {
                ++counter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter > 0)
            return true;

        return false;
    }
}
