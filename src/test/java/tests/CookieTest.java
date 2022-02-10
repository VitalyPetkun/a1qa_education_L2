package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.RegistrationPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

public class CookieTest extends BaseTest {

    @Test
    public void acceptCookie() {
        SmartLogger.logStep(1, "Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        SmartLogger.logStep(2, "Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        RegistrationPageSteps.assertIsCookieFormOpen();

        SmartLogger.logStep(3, "Click button for accept all cookies");
        RegistrationPageSteps.notReallyNoBtnClick();
        RegistrationPageSteps.assertIsCookieFormHidden();
    }
}
