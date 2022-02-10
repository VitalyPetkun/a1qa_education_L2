package tests;

import aquality.selenium.browser.AqualityServices;
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
        SmartLogger.logStep(1, "Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        SmartLogger.logStep(2, "Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        start = System.nanoTime();
        RegistrationPageSteps.assertIsRegistrationPageOpen();
        finish = System.nanoTime();

        SmartLogger.logStep(3, "Get start time");
        durationInMillisecond = (finish - start) / Integer.parseInt(PropertiesManager.getTestDataValue("nanosecondToMillisecondConverter"));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        RegistrationPageSteps.assertIsTimerStartFromZero(
                new SimpleDateFormat("HH:mm:ss").format(durationInMillisecond));
    }
}
