package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;

public class CookieTest extends BaseTest {

    @Test
    public void acceptCookie() {
        LOGGER.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        LOGGER.info("Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        RegistrationPageSteps.assertIsCookieFormOpen();

        LOGGER.info("Click button for accept all cookies");
        RegistrationPageSteps.notReallyNoBtnClick();
        RegistrationPageSteps.assertIsCookieFormHidden();
    }
}
