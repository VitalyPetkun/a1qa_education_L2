package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;

public class TimerTest extends BaseTest{

    @Test
    public void checkTimer() {
        logger.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        logger.info("Click link for navigate to login form");
        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsRegistrationPageOpen();

        RegistrationPageSteps.assertIsTimerStartFromZero();
    }
}
