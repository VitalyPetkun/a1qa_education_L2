package utils.dataBases.union_Reporting.tables;

import utils.SmartLogger;
import utils.dataBases.union_Reporting.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static services.DataBaseConst.*;

public class Project {

    private Project() {
    }

    public static void add(String projectName) {
        SmartLogger.logInfo("Add project name");
        ResultSet resultSet = get(projectName);
        if (!DataBaseHandler.isItem(resultSet)) {
            String insert = "INSERT INTO ".concat(PROJECT_TABLE.getConst()).concat("(").
                    concat(PROJECT_NAME.getConst()).concat(")").concat("VALUES(?)");

            try {
                PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1, projectName);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet get(String projectName) {
        SmartLogger.logInfo("Get project name");
        ResultSet resultSet = null;
        String select = "SELECT * FROM ".concat(PROJECT_TABLE.getConst()).concat(" WHERE ").
                concat(PROJECT_NAME.getConst()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, projectName);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
