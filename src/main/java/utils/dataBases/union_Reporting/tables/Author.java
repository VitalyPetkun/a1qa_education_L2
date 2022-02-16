package utils.dataBases.union_Reporting.tables;

import models.AuthorModel;
import utils.SmartLogger;
import utils.dataBases.union_Reporting.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static services.DataBaseConst.*;

public class Author {

    private Author() {
    }

    public static void add(AuthorModel author) {
        SmartLogger.logInfo("Add author data");
        ResultSet resultSet = get(author);
        if (!DataBaseHandler.isItem(resultSet)) {
            String insert = "INSERT INTO ".concat(AUTHOR_TABLE.getConst()).concat("(").
                    concat(AUTHOR_NAME.getConst()).concat(",").concat(AUTHOR_LOGIN.getConst()).concat(",").
                    concat(AUTHOR_EMAIL.getConst()).concat(")").concat("VALUES(?,?,?)");

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
        String select = "SELECT * FROM ".concat(AUTHOR_TABLE.getConst()).concat(" WHERE ").
                concat(AUTHOR_NAME.getConst()).concat("=? AND ").concat(AUTHOR_LOGIN.getConst()).concat("=? AND ").
                concat(AUTHOR_EMAIL.getConst()).concat("=?");

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
