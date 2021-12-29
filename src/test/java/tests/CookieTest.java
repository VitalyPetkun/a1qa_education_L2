package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.WelcomePageSteps;
import steps.registrationsteps.RegistrationPageSteps;
import utils.ConfigManager;

public class CookieTest extends BaseTest{
    private WelcomePageSteps welcomePageSteps;
    private RegistrationPageSteps registrationPageSteps;

    @Test
    public void acceptCookie() {
        welcomePageSteps = new WelcomePageSteps();
        registrationPageSteps = new RegistrationPageSteps();

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        welcomePageSteps.isOpen();

        welcomePageSteps.clickLinkNextPage();
        registrationPageSteps.getCookieFormSteps().clickButtonNotReallyNo();
        registrationPageSteps.getCookieFormSteps().isClose();
    }
}
