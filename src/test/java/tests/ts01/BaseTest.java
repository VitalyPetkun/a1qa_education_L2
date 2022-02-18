package tests.ts01;

import aquality.selenium.browser.AqualityServices;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.SmartLogger;
import utils.dataBase.unionReporting.DataBaseUnionReporting;

import static services.dataBaseUnionReporting.ProjectsName.*;

public class BaseTest {

    private String projectName = PROJECT_USERINYERFACE.getName();
    private long startTime;
    private long endTime;
    private int buildNumber;

    @BeforeMethod
    protected void setup() {
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
        SmartLogger.logInfo("Add result test in data base");
        DataBaseUnionReporting.addResultTest(result, projectName, startTime, endTime, buildNumber);
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
        SmartLogger.logInfo("Checking result test in data base");
        DataBaseUnionReporting.isTestAdd(result, projectName, startTime, endTime);
    }
}