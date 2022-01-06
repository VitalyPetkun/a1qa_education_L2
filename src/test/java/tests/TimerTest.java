package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;

public class TimerTest extends BaseTest{

    @Test
    public void checkTimer() {

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsRegistrationPageOpen();

        RegistrationPageSteps.assertIsTimerStartFromZero();
    }
}
