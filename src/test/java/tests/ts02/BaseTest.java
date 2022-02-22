package tests.ts02;

import aquality.selenium.browser.AqualityServices;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.dataBase.unionReporting.DataBaseUnionReporting;

import java.util.ArrayList;
import java.util.List;

import static services.ConfigVariables.PROJECT_NAME;

public class BaseTest {

    private static final int copyTestNumber = Integer.parseInt(PropertiesManager.getConfigValue("idQuantityLimitation"));

    private long startTime;
    private long endTime;
    private static int sessionBuildNumber;
    private List<Integer> copyTestsId = new ArrayList<>();

    @BeforeMethod
    protected void setup() {
        SmartLogger.logInfo("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        SmartLogger.logInfo("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
        DataBaseUnionReporting.copyTestsInDataBase(PROJECT_NAME.getVariable());
        copyTestsId = DataBaseUnionReporting.getCopyTestsId(copyTestNumber);
        startTime = System.currentTimeMillis();
        ++sessionBuildNumber;
    }

    @AfterMethod
    protected void quitDriver(ITestResult result) {
        endTime = System.currentTimeMillis();
        DataBaseUnionReporting.addResultTest(result, PROJECT_NAME.getVariable(), startTime, endTime, sessionBuildNumber);
        DataBaseUnionReporting.deleteCopyTest(copyTestsId);
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }
}