package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.SmartLogger;

import static services.ProjectsName.*;

public class BaseTest {

    private String projectName = PROJECTS_USERINYERFACE.getName();
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
        DataBase.postResultTest(projectName, result, startTime, endTime, buildNumber);
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }

}