package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;

public class RegistrationTest extends BaseTest{

    @Test
    public void registration() {

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsLoginFormOpen();

        RegistrationPageSteps.passwordInput();
        RegistrationPageSteps.emailInput();
        RegistrationPageSteps.domainInput();
        RegistrationPageSteps.domainExtensionsItemClick();
        RegistrationPageSteps.termsAndConditionsCheck();
        RegistrationPageSteps.loginFormBtnNextClick();
        RegistrationPageSteps.assertIsAvatarAndInterestsFormOpen();

        RegistrationPageSteps.uploadAvatarIcon();
        RegistrationPageSteps.threeRandomInterestsSelect();
        RegistrationPageSteps.assertIsPersonalDetailsFormOpen();
    }
}
