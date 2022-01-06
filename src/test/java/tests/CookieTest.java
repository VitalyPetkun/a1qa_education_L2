package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;

public class CookieTest extends BaseTest{

    @Test
    public void acceptCookie() {
        logger.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        logger.info("Click link for navigate to login form");
        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsCookieFormOpen();

        logger.info("Click button for accept all cookies");
        RegistrationPageSteps.btnNotReallyNoClick();
        RegistrationPageSteps.assertIsCookieFormHidden();
    }
}
