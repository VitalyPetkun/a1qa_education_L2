package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimerTest extends BaseTest {

    private long start;
    private long finish;
    private long durationInMillisecond;

    @Test
    public void checkTimer() {
        LOGGER.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        LOGGER.info("Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        start = System.nanoTime();
        RegistrationPageSteps.assertIsRegistrationPageOpen();
        finish = System.nanoTime();

        LOGGER.info("Get start time");
        durationInMillisecond = (finish - start) / ConfigManager.getTestDataLong("nanosecondToMillisecondConverter");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        RegistrationPageSteps.assertIsTimerStartFromZero(
                new SimpleDateFormat("HH:mm:ss").format(durationInMillisecond));
    }
}
