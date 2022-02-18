package tests.ts02;

import aquality.selenium.browser.AqualityServices;
import models.TestModel;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.dataBase.DataBaseHandler;
import utils.dataBase.unionReporting.DataBaseUnionReporting;
import utils.dataBase.unionReporting.tables.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static services.dataBaseUnionReporting.DataBaseUnionReporting.*;
import static services.dataBaseUnionReporting.ProjectsName.PROJECT_USERINYERFACE;

public class BaseTest {

    private String projectName = PROJECT_USERINYERFACE.getName();
    private long startTime;
    private long endTime;
    private int buildNumber;
    private List<Integer> copyTestsId = new ArrayList<>();
    private ResultSet resultSet;
    private int testsSize;
    private int copyTestNumber = Integer.parseInt(PropertiesManager.getConfigValue("idQuantityLimitation"));
    private List<TestModel> tests = new ArrayList<>();

    @BeforeMethod
    protected void setup() {
        SmartLogger.logInfo("Copy test");
        DataBaseUnionReporting.copyTestsInDataBase(projectName);
        resultSet = Test.getSize();
        testsSize = DataBaseHandler.resultSetSize(resultSet);
        try {
            for (int i = 0; i < testsSize - copyTestNumber; i++) {
                resultSet.next();
            }
            for (int i = testsSize - copyTestNumber; i < testsSize; i++) {
                resultSet.next();
                copyTestsId.add(resultSet.getInt(TEST_ID.getValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ++buildNumber;
        SmartLogger.logInfo("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        SmartLogger.logInfo("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
        startTime = System.currentTimeMillis();
    }

    @AfterMethod
    protected void quitDriver(ITestResult result) {
        endTime = System.currentTimeMillis();
        DataBaseUnionReporting.addResultTest(result, projectName, startTime, endTime, buildNumber);
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
        SmartLogger.logInfo("Delete copy test");
        for (int i = 0; i < copyTestsId.size(); i++) {
            Test.delete(copyTestsId.get(i));
        }
    }

}