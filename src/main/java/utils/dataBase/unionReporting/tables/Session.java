package utils.dataBase.unionReporting.tables;

import models.SessionModel;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static services.dataBaseUnionReporting.DataBaseUnionReporting.*;

public class Session {

    private Session() {
    }

    public static void add(SessionModel session) {
        SmartLogger.logInfo("Add test session");
        String insert = "INSERT INTO ".concat(SESSION_TABLE.getValue()).concat("(").
                concat(SESSION_SESSION_KEY.getValue()).concat(",").concat(SESSION_CREATED_TIME.getValue()).concat(",").
                concat(SESSION_BUILD_NUMBER.getValue()).concat(")").concat("VALUES(?,?,?)");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, session.getSessionKey());
            preparedStatement.setString(2, session.getCreatedTime());
            preparedStatement.setString(3, session.getBuildNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ResultSet get(SessionModel session) {
        SmartLogger.logInfo("Get test session");
        ResultSet resultSet = null;
        String select = "SELECT * FROM ".concat(SESSION_TABLE.getValue()).concat(" WHERE ").
                concat(SESSION_SESSION_KEY.getValue()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, session.getSessionKey());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
