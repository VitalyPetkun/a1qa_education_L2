package utils.dataBases.union_Reporting.tables;

import org.testng.ITestResult;
import utils.SmartLogger;
import utils.dataBases.union_Reporting.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static services.DataBaseConst.*;

public class Status {

    private Status() {
    }

    public static ResultSet get(ITestResult result) {
        SmartLogger.logInfo("Get test status");
        ResultSet resultSet = null;
        String select = "SELECT * FROM ".concat(STATUS_TABLE.getConst()).concat(" WHERE ").
                concat(STATUS_NAME.getConst()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, getStatusName(result));

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    private static String getStatusName(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                return "FAILED";
            case ITestResult.SKIP:
                return "SKIPPED";
            default:
                return "PASSED";
        }
    }
}
