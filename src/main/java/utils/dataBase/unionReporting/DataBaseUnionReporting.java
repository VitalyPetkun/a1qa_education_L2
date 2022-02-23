package utils.dataBase.unionReporting;

import aquality.selenium.browser.AqualityServices;
import models.AuthorModel;
import models.SessionModel;
import models.TestModel;
import org.testng.Assert;
import org.testng.ITestResult;
import services.dataBaseUnionReporting.AuthorParameters;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import utils.dataBase.unionReporting.tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static services.ConfigVariables.*;
import static services.dataBaseUnionReporting.DataBaseUnionReportingTablesValues.*;
import static services.dataBaseUnionReporting.DataBaseUnionReportingValues.*;

public class DataBaseUnionReporting {

    private static final int idQuantityLimitation = Integer.parseInt(PropertiesManager.getTestDataValue(ID_QUANTITY_LIMITATION.getValue()));
    private static final int testTableMinId = Integer.parseInt(PropertiesManager.getTestDataValue(TEST_TABLE_MIN_ID.getValue()));
    private static final String env = System.getenv().get(PropertiesManager.getConfigValue(ENV.getVariable()));
    private static final String timeZone = PropertiesManager.getConfigValue(TIME_ZONE.getVariable());

    private static TestModel test = new TestModel();
    private static AuthorModel author = new AuthorModel();
    private static SessionModel session = new SessionModel();
    private static ResultSet resultSet;

    private DataBaseUnionReporting() {
    }

    public static void addResultTest(ITestResult result, String projectName, long startTime, long endTime, int buildNumber) {
        SmartLogger.logInfo("Add result test in dataBase");
        ProjectTable.add(projectName);
        setSession(startTime, buildNumber);
        SessionTable.add(session);
        setAuthor();
        AuthorTable.add(author);
        setTest(result, projectName, startTime, endTime);
        TestTable.add(test);
    }

    private static TestModel setTest(ITestResult result, String projectName, long startTime, long endTime) {
        SmartLogger.logInfo("Set test data");
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        try {
            test.setName(result.getInstanceName());
            resultSet = StatusTable.get(result);
            resultSet.next();
            test.setStatusId(resultSet.getInt(STATUS_ID.getValue()));
            resultSet = ProjectTable.get(projectName);
            resultSet.next();
            test.setProjectId(resultSet.getInt(PROJECT_ID.getValue()));
            resultSet = SessionTable.get(session);
            resultSet.next();
            test.setSessionId(resultSet.getInt(SESSION_ID.getValue()));
            test.setStartTime(startTime);
            test.setEndTime(endTime);
            test.setEnv(env);
            test.setBrowser(AqualityServices.getBrowser().getBrowserName().toString());
            resultSet = AuthorTable.get(author);
            resultSet.next();
            test.setAuthorId(resultSet.getInt(AUTHOR_ID.getValue()));
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return test;
    }

    private static void setAuthor() {
        SmartLogger.logInfo("Set project author");
        author.setName(PropertiesManager.getConfigValue(AuthorParameters.AUTHOR_NAME.getParameter()));
        author.setLogin(PropertiesManager.getConfigValue(AuthorParameters.AUTHOR_LOGIN.getParameter()));
        author.setEmail(PropertiesManager.getConfigValue(AuthorParameters.AUTHOR_EMAIL.getParameter()));
    }

    private static void setSession(long startTime, int buildNumber) {
        SmartLogger.logInfo("Set test session");
        session.setSessionKey(startTime);
        session.setCreatedTime(startTime);
        session.setBuildNumber(buildNumber);
    }

    public static void isTestAdd(ITestResult result, String projectName, long startTime, long endTime) {
        SmartLogger.logInfo("Checking add test");
        resultSet = TestTable.get(setTest(result, projectName, startTime, endTime));
        Assert.assertTrue(DataBaseHandler.isItem(resultSet));
    }

    public static void copyTestsInDataBase(String projectName) {
        SmartLogger.logInfo("Copy tests");
        int randomId;
        resultSet = TestTable.getSize();
        int resultSetSize = DataBaseHandler.resultSetSize(resultSet);
        try {
            for (int i = 0; i < idQuantityLimitation; i++) {
                resultSet.beforeFirst();
                resultSet.next();

                randomId = DataBaseHandler.getRandomId(resultSetSize, testTableMinId);

                while (!(randomId == resultSet.getInt(TEST_ID.getValue())))
                    resultSet.next();

                test.setName(resultSet.getString(TEST_NAME.getValue()));

                int statusId = Integer.parseInt(resultSet.getString(TEST_STATUS_ID.getValue()));
                test.setStatusId(statusId);

                ResultSet projectResultSet = ProjectTable.get(projectName);
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

                ResultSet authorResultSet = AuthorTable.get(author);
                authorResultSet.next();
                test.setAuthorId(authorResultSet.getInt(AUTHOR_ID.getValue()));

                TestTable.add(test);
            }
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }
    }

    public static List<Integer> getCopyTestsId(int copyTestNumber) {
        SmartLogger.logInfo("Get copy tests id");
        List<Integer> copyTestsId = new ArrayList<>();
        resultSet = TestTable.getSize();
        int testsSize = DataBaseHandler.resultSetSize(resultSet);

        try {
            for (int i = 0; i < testsSize - copyTestNumber; i++) {
                resultSet.next();
            }
            for (int i = testsSize - copyTestNumber; i < testsSize; i++) {
                resultSet.next();
                copyTestsId.add(resultSet.getInt(TEST_ID.getValue()));
            }
        } catch (SQLException e) {
            SmartLogger.logError("ResultSet is null");
        }

        return copyTestsId;
    }

    public static void deleteCopyTest(List<Integer> copyTestsId) {
        SmartLogger.logInfo("Delete copy test");
        for (int i = 0; i < copyTestsId.size(); i++) {
            TestTable.delete(copyTestsId.get(i));
        }
    }
}
