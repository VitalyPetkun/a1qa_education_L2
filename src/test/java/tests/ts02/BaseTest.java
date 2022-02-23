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
import static services.dataBaseUnionReporting.DataBaseUnionReportingValues.ID_QUANTITY_LIMITATION;

public class BaseTest {

    private final String projectName = PropertiesManager.getConfigValue(PROJECT_NAME.getVariable());
    private final int copyTestNumber = Integer.parseInt(PropertiesManager.getTestDataValue(ID_QUANTITY_LIMITATION.getValue()));

    private static int sessionBuildNumber;

    private List<Integer> copyTestsId = new ArrayList<>();

    private long startTime;
    private long endTime;

    @BeforeMethod
    protected void setup() {
        SmartLogger.logInfo("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        SmartLogger.logInfo("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
        DataBaseUnionReporting.copyTestsInDataBase(projectName);
        copyTestsId = DataBaseUnionReporting.getCopyTestsId(copyTestNumber);
        SmartLogger.logInfo("Get test start time");
        startTime = System.currentTimeMillis();
        ++sessionBuildNumber;
    }

    @AfterMethod
    protected void quitDriver(ITestResult result) {
        SmartLogger.logInfo("Get test end time");
        endTime = System.currentTimeMillis();
        DataBaseUnionReporting.addResultTest(result, projectName, startTime, endTime, sessionBuildNumber);
        DataBaseUnionReporting.deleteCopyTest(copyTestsId);
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }
}