package utils.dataBase.unionReporting.tables;

import models.AuthorModel;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static services.dataBaseUnionReporting.DataBaseUnionReporting.*;

public class Author {

    private Author() {
    }

    public static void add(AuthorModel author) {
        SmartLogger.logInfo("Add author data");
        ResultSet resultSet = get(author);
        if (!DataBaseHandler.isItem(resultSet)) {
            String insert = "INSERT INTO ".concat(AUTHOR_TABLE.getValue()).concat("(").
                    concat(AUTHOR_NAME.getValue()).concat(",").concat(AUTHOR_LOGIN.getValue()).concat(",").
                    concat(AUTHOR_EMAIL.getValue()).concat(")").concat("VALUES(?,?,?)");

            try {
                PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getLogin());
                preparedStatement.setString(3, author.getEmail());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet get(AuthorModel author) {
        SmartLogger.logInfo("Get author data");
        ResultSet resultSet = null;
        String select = "SELECT * FROM ".concat(AUTHOR_TABLE.getValue()).concat(" WHERE ").
                concat(AUTHOR_NAME.getValue()).concat("=? AND ").concat(AUTHOR_LOGIN.getValue()).concat("=? AND ").
                concat(AUTHOR_EMAIL.getValue()).concat("=?");

        try {
            PreparedStatement preparedStatement = DataBaseHandler.getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getLogin());
            preparedStatement.setString(3, author.getEmail());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
