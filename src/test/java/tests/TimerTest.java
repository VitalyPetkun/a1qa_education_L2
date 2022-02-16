package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.Reporter;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimerTest extends BaseTest {

    private long start;
    private long finish;
    private long durationInMillisecond;

    @Test
    public void checkTimer() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SmartLogger.logStep(1, "Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        SmartLogger.logStep(2, "Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        start = System.currentTimeMillis();
        RegistrationPageSteps.assertIsRegistrationPageOpen();
        finish = System.currentTimeMillis();

        SmartLogger.logStep(3, "Get start time");
        durationInMillisecond = finish - start;
        RegistrationPageSteps.assertIsTimerStartFromZero(new SimpleDateFormat("HH:mm:ss").format(durationInMillisecond));
    }
}
