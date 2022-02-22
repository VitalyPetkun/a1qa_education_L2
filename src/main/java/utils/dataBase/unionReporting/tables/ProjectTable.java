package utils.dataBase.unionReporting.tables;

import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static services.dataBaseUnionReporting.DataBaseUnionReportingTablesValues.*;

public class ProjectTable {

    private ProjectTable() {
    }

    public static void add(String projectName) {
        SmartLogger.logInfo("Add project name");
        ResultSet resultSet = get(projectName);
        if (!DataBaseHandler.isItem(resultSet)) {
            String insert = "INSERT INTO ".concat(PROJECT_TABLE.getValue()).concat("(").
                    concat(PROJECT_NAME.getValue()).concat(")").concat("VALUES(?)");

            try {
                PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1, projectName);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                SmartLogger.logError("ResultSet is null");
            }
        }
    }

    public static ResultSet get(String projectName) {
        SmartLogger.logInfo("Get project name");
        ResultSet resultSet = null;
        String select = "SELECT * FROM ".concat(PROJECT_TABLE.getValue()).concat(" WHERE ").
                concat(PROJECT_NAME.getValue()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, projectName);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return resultSet;
    }
}
