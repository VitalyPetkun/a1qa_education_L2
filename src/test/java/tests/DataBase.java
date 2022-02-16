package tests;

import aquality.selenium.browser.AqualityServices;
import models.AuthorModel;
import models.SessionModel;
import models.TestModel;
import org.testng.ITestResult;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.dataBases.union_Reporting.tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;

import static services.AuthorParameters.*;

public class DataBase {

    private static TestModel test = new TestModel();
    private static AuthorModel author = new AuthorModel();
    private static SessionModel session = new SessionModel();
    private static ResultSet resultSet;

    private DataBase() {
    }

    protected static void postResultTest(String projectName, ITestResult result, long startTime, long endTime, int buildNumber) {
        Project.add(projectName);
        setSession(startTime, buildNumber);
        Session.add(session);
        setAuthor();
        Author.add(author);

        try {
            setTest(result, projectName, startTime, endTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Test.add(test);
    }

    private static void setTest(ITestResult result, String projectName, long startTime, long endTime) throws SQLException {
        SmartLogger.logInfo("Set test data");
        TimeZone.setDefault(TimeZone.getTimeZone(PropertiesManager.getConfigValue("timeZone")));
        test.setName(result.getInstanceName());
        resultSet = Status.get(result);
        resultSet.next();
        test.setStatusId(resultSet.getInt("id"));
        resultSet = Project.get(projectName);
        resultSet.next();
        test.setProjectId(resultSet.getInt("id"));
        resultSet = Session.get(session);
        resultSet.next();
        test.setSessionId(resultSet.getInt("id"));
        test.setStartTime(startTime);
        test.setEndTime(endTime);
        test.setEnv(System.getenv().get("COMPUTERNAME"));
        test.setBrowser(AqualityServices.getBrowser().getBrowserName().toString());
        resultSet = Author.get(author);
        resultSet.next();
        test.setAuthorId(resultSet.getInt("id"));
    }

    private static void setAuthor() {
        SmartLogger.logInfo("Set project author");
        author.setName(VITALY_PETKUN_NAME.getParameter());
        author.setLogin(VITALY_PETKUN_LOGIN.getParameter());
        author.setEmail(VITALY_PETKUN_EMAIL.getParameter());
    }

    private static void setSession(long startTime, int buildNumber) {
        SmartLogger.logInfo("Set test session");
        session.setSessionKey(startTime);
        session.setCreatedTime(startTime);
        session.setBuildNumber(buildNumber);
    }
}
