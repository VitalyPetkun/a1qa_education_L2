package tests.ts01;

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
        WelcomePageSteps.assertIsOpenWelcomePage();

        SmartLogger.logStep(2, "Click link for navigate to login form");
        WelcomePageSteps.clickNextPageLnk();
        RegistrationPageSteps.assertIsOpenCookieForm();

        SmartLogger.logStep(3, "Click button for accept all cookies");
        RegistrationPageSteps.clickNotReallyNoBtn();
        RegistrationPageSteps.assertIsCloseCookieForm();
    }
}
