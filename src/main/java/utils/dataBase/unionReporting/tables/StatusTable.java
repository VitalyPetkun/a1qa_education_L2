package utils.dataBase.unionReporting.tables;

import org.testng.ITestResult;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static services.dataBaseUnionReporting.DataBaseUnionReportingTablesValues.*;

public class StatusTable {

    private StatusTable() {
    }

    public static ResultSet get(ITestResult result) {
        SmartLogger.logInfo("Get test status");
        ResultSet resultSet = null;
        String select = "SELECT * FROM ".concat(STATUS_TABLE.getValue()).concat(" WHERE ").
                concat(STATUS_NAME.getValue()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.dbConnection().prepareStatement(select);
            preparedStatement.setString(1, getStatusName(result));

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return resultSet;
    }

    private static String getStatusName(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                return "PASSED";
            case ITestResult.FAILURE:
                return "FAILED";
            case ITestResult.SKIP:
                return "SKIPPED";
        }

        return null;
    }
}
