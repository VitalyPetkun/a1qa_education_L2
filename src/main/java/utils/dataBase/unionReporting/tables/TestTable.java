package utils.dataBase.unionReporting.tables;

import models.TestModel;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static services.dataBaseUnionReporting.DataBaseUnionReportingTablesValues.*;

public class TestTable {

    private static ResultSet resultSet = null;
    private static int result = 0;

    private TestTable() {
    }

    public static int add(TestModel test) {
        SmartLogger.logInfo("Add test data");
        String insert = "INSERT INTO ".concat(TEST_TABLE.getValue()).concat("(").
                concat(TEST_NAME.getValue()).concat(",").concat(TEST_STATUS_ID.getValue()).concat(",").
                concat(TEST_METHOD_NAME.getValue()).concat(",").concat(TEST_PROJECT_ID.getValue()).concat(",").
                concat(TEST_SESSION_ID.getValue()).concat(",").concat(TEST_START_TIME.getValue()).concat(",").
                concat(TEST_END_TIME.getValue()).concat(",").concat(TEST_ENV.getValue()).concat(",").
                concat(TEST_BROWSER.getValue()).concat(",").concat(TEST_AUTHOR_ID.getValue()).
                concat(")").concat("VALUES(?,?,?,?,?,?,?,?,?,?)");

        try (PreparedStatement preparedStatement = DataBaseHandler.dbConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, test.getName());
            preparedStatement.setInt(2, test.getStatusId());
            preparedStatement.setString(3, test.getName());
            preparedStatement.setInt(4, test.getProjectId());
            preparedStatement.setInt(5, test.getSessionId());
            preparedStatement.setString(6, test.getStartTime());
            preparedStatement.setString(7, test.getEndTime());
            preparedStatement.setString(8, test.getEnv());
            preparedStatement.setString(9, test.getBrowser());
            preparedStatement.setInt(10, test.getAuthorId());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return result;
    }

    public static ResultSet get(TestModel test) {
        SmartLogger.logInfo("Get test data");
        String select = "SELECT * FROM ".concat(TEST_TABLE.getValue()).concat(" WHERE ").
                concat(TEST_NAME.getValue()).concat("=? AND ").concat(TEST_PROJECT_ID.getValue()).concat("=? AND ").
                concat(TEST_AUTHOR_ID.getValue()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.dbConnection().prepareStatement(select);
            preparedStatement.setString(1, test.getName());
            preparedStatement.setInt(2, test.getProjectId());
            preparedStatement.setInt(3, test.getAuthorId());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return resultSet;
    }

    public static ResultSet getSize() {
        SmartLogger.logInfo("Get tests data");
        String select = "SELECT * FROM ".concat(TEST_TABLE.getValue());

        try {
            PreparedStatement preparedStatement = DataBaseHandler.dbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return resultSet;
    }

    public static int delete(int id) {
        SmartLogger.logInfo("Delete test data");
        String select = "DELETE FROM ".concat(TEST_TABLE.getValue()).concat(" WHERE ").
                concat(TEST_ID.getValue()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.dbConnection().prepareStatement(select);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return result;
    }
}
