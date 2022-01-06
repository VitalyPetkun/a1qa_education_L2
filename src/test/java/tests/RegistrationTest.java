package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;


public class RegistrationTest extends BaseTest{

    @Test
    public void registration() {
        logger.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        logger.info("Click link for navigate to login form");
        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsLoginFormOpen();

        logger.info("Password input");
        RegistrationPageSteps.passwordInput();
        logger.info("E-mail input");
        RegistrationPageSteps.emailInput();
        logger.info("Domain input");
        RegistrationPageSteps.domainInput();
        logger.info("Domain extensions select");
        RegistrationPageSteps.domainExtensionsItemClick();
        logger.info("Accept terms and conditions of use");
        RegistrationPageSteps.termsAndConditionsCheck();
        logger.info("Click button for navigate to avatar and interests form");
        RegistrationPageSteps.loginFormBtnNextClick();
        RegistrationPageSteps.assertIsAvatarAndInterestsFormOpen();

        logger.info("Upload avatar icon");
        RegistrationPageSteps.uploadAvatarIcon();
        logger.info("Three random interests select");
        RegistrationPageSteps.threeRandomInterestsSelect();
        logger.info("Click button for navigate to personal details form");
        RegistrationPageSteps.avatarAndInterestsFormBtnNextClick();
        RegistrationPageSteps.assertIsPersonalDetailsFormOpen();
    }
}
