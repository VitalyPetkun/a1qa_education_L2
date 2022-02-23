package tests.ts01;

import aquality.selenium.browser.AqualityServices;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.dataBase.unionReporting.DataBaseUnionReporting;

import static services.ConfigVariables.*;

public class BaseTest {

    private final String projectName = PropertiesManager.getConfigValue(PROJECT_NAME.getVariable());

    private static int sessionBuildNumber;

    private long startTime;
    private long endTime;


    @BeforeMethod
    protected void setup() {
        SmartLogger.logInfo("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        SmartLogger.logInfo("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
        SmartLogger.logInfo("Get test start time");
        startTime = System.currentTimeMillis();
        ++sessionBuildNumber;
    }

    @AfterMethod
    protected void quitDriver(ITestResult result) {
        SmartLogger.logInfo("Get test end time");
        endTime = System.currentTimeMillis();
        SmartLogger.logInfo("Add result test in data base");
        DataBaseUnionReporting.addResultTest(result, projectName, startTime, endTime, sessionBuildNumber);
        SmartLogger.logInfo("Checking result test in data base");
        DataBaseUnionReporting.isTestAdd(result, projectName, startTime, endTime);
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }
}