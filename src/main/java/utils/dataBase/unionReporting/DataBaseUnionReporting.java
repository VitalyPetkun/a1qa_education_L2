package utils.dataBase.unionReporting;

import aquality.selenium.browser.AqualityServices;
import models.AuthorModel;
import models.SessionModel;
import models.TestModel;
import org.testng.Assert;
import org.testng.ITestResult;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import utils.dataBase.unionReporting.tables.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;
import static services.dataBaseUnionReporting.AuthorParameters.*;
import static services.dataBaseUnionReporting.DataBaseUnionReporting.*;

public class DataBaseUnionReporting {

    private static TestModel test = new TestModel();
    private static AuthorModel author = new AuthorModel();
    private static SessionModel session = new SessionModel();
    private static ResultSet resultSet;

    private DataBaseUnionReporting() {
    }

    public static void addResultTest(ITestResult result, String projectName, long startTime, long endTime, int buildNumber) {
        Project.add(projectName);
        setSession(startTime, buildNumber);
        Session.add(session);
        setAuthor();
        Author.add(author);
        setTest(result, projectName, startTime, endTime);
        Test.add(test);
    }

    private static TestModel setTest(ITestResult result, String projectName, long startTime, long endTime) {
        SmartLogger.logInfo("Set test data");
        TimeZone.setDefault(TimeZone.getTimeZone(PropertiesManager.getConfigValue("timeZone")));
        try {
            test.setName(result.getInstanceName());
            resultSet = Status.get(result);
            resultSet.next();
            test.setStatusId(resultSet.getInt(STATUS_ID.getValue()));
            resultSet = Project.get(projectName);
            resultSet.next();
            test.setProjectId(resultSet.getInt(PROJECT_ID.getValue()));
            resultSet = Session.get(session);
            resultSet.next();
            test.setSessionId(resultSet.getInt(SESSION_ID.getValue()));
            test.setStartTime(startTime);
            test.setEndTime(endTime);
            test.setEnv(System.getenv().get(PropertiesManager.getConfigValue("env")));
            test.setBrowser(AqualityServices.getBrowser().getBrowserName().toString());
            resultSet = Author.get(author);
            resultSet.next();
            test.setAuthorId(resultSet.getInt(AUTHOR_ID.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
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

    public static void isTestAdd(ITestResult result, String projectName, long startTime, long endTime) {
        resultSet = Test.get(setTest(result, projectName, startTime, endTime));
        Assert.assertTrue(DataBaseHandler.isItem(resultSet));
    }

    public static void copyTestsInDataBase(String projectName) {
        int randomId;
        resultSet = Test.getSize();
        int resultSetSize = DataBaseHandler.resultSetSize(resultSet);
        try {
            for (int i = 0; i < Integer.parseInt(PropertiesManager.getConfigValue("idQuantityLimitation")); i++) {
                resultSet.beforeFirst();
                resultSet.next();

                randomId = DataBaseHandler.randomId(resultSetSize, Integer.parseInt(PropertiesManager.getConfigValue("minId")));

                while (!(randomId == resultSet.getInt(TEST_ID.getValue())))
                    resultSet.next();

                test.setName(resultSet.getString(TEST_NAME.getValue()));

                int statusId = Integer.parseInt(resultSet.getString(TEST_STATUS_ID.getValue()));
                test.setStatusId(statusId);

                ResultSet projectResultSet = Project.get(projectName);
                projectResultSet.next();
                test.setProjectId(projectResultSet.getInt(PROJECT_ID.getValue()));

                int sessionId = Integer.parseInt(resultSet.getString(TEST_SESSION_ID.getValue()));
                test.setSessionId(sessionId);

                long startTime = Long.parseLong(resultSet.getString(TEST_START_TIME.getValue()));
                test.setStartTime(startTime);

                long endTime = Long.parseLong(resultSet.getString(TEST_END_TIME.getValue()));
                test.setEndTime(endTime);

                test.setEnv(resultSet.getString(TEST_ENV.getValue()));

                test.setBrowser(resultSet.getString(TEST_BROWSER.getValue()));

                ResultSet authorResultSet = Author.get(author);
                authorResultSet.next();
                test.setAuthorId(authorResultSet.getInt(AUTHOR_ID.getValue()));

                Test.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
