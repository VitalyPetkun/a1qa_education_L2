package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.ConfigManager;

public class CookieTest extends BaseTest{

    @Test
    public void acceptCookie() {

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsCookieFormOpen();

        RegistrationPageSteps.btnNotReallyNoClick();
        RegistrationPageSteps.assertIsCookieFormHidden();
    }
}
