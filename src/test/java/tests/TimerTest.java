package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimerTest extends BaseTest {

    private GregorianCalendar start;
    private GregorianCalendar finish;

    private double durationInSeconds;

    @Test
    public void checkTimer() {
        LOGGER.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        LOGGER.info("Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        start = new GregorianCalendar();
        RegistrationPageSteps.assertIsRegistrationPageOpen();
        finish = new GregorianCalendar();

        LOGGER.info("Get start time");
        durationInSeconds = (finish.getTimeInMillis() - start.getTimeInMillis()) *
                ConfigManager.getTestDataDouble("convertingTimeValue");
        RegistrationPageSteps.assertIsTimerStartFromZero(
                new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(durationInSeconds));
    }
}
