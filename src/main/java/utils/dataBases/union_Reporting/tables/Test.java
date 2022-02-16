package utils.dataBases.union_Reporting.tables;

import models.TestModel;
import utils.SmartLogger;
import utils.dataBases.union_Reporting.DataBaseHandler;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static services.DataBaseConst.*;

public class Test {

    private Test() {}

    public static void add(TestModel test) {
        SmartLogger.logInfo("Add test data");
        String insert = "INSERT INTO ".concat(TEST_TABLE.getConst()).concat("(").
                concat(TEST_NAME.getConst()).concat(",").concat(TEST_STATUS_ID.getConst()).concat(",").
                concat(TEST_METHOD_NAME.getConst()).concat(",").concat(TEST_PROJECT_ID.getConst()).concat(",").
                concat(TEST_SESSION_ID.getConst()).concat(",").concat(TEST_START_TIME.getConst()).concat(",").
                concat(TEST_END_TIME.getConst()).concat(",").concat(TEST_ENV.getConst()).concat(",").
                concat(TEST_BROWSER.getConst()).concat(",").concat(TEST_AUTHOR_ID.getConst()).
                concat(")").concat("VALUES(?,?,?,?,?,?,?,?,?,?)");

        try(PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, test.getName());
            preparedStatement.setString(2, test.getStatusId());
            preparedStatement.setString(3, test.getName());
            preparedStatement.setString(4, test.getProjectId());
            preparedStatement.setString(5, test.getSessionId());
            preparedStatement.setString(6, test.getStartTime());
            preparedStatement.setString(7, test.getEndTime());
            preparedStatement.setString(8, test.getEnv());
            preparedStatement.setString(9, test.getBrowser());
            preparedStatement.setString(10, test.getAuthorId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
