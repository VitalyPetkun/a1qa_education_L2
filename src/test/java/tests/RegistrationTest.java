package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;


public class RegistrationTest extends BaseTest {

    @Test
    public void registration() {
        LOGGER.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        LOGGER.info("Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        RegistrationPageSteps.assertIsLoginFormOpen();

        LOGGER.info("Password input");
        RegistrationPageSteps.passwordTxtInput();
        LOGGER.info("E-mail input");
        RegistrationPageSteps.emailTxtInput();
        LOGGER.info("Domain input");
        RegistrationPageSteps.domainTxtInput();
        LOGGER.info("Domain extensions select");
        RegistrationPageSteps.domainExtensionsItemClick();
        LOGGER.info("Accept terms and conditions of use");
        RegistrationPageSteps.termsAndConditionsChkCheck();
        LOGGER.info("Click button for navigate to avatar and interests form");
        RegistrationPageSteps.loginFormNextBtnClick();
        RegistrationPageSteps.assertIsAvatarAndInterestsFormOpen();

        LOGGER.info("Upload avatar icon");
        RegistrationPageSteps.uploadAvatarIcon();
        LOGGER.info("Three random interests select");
        RegistrationPageSteps.threeRandomInterestsSelect();
        LOGGER.info("Click button for navigate to personal details form");
        RegistrationPageSteps.avatarAndInterestsFormNextBtnClick();
        RegistrationPageSteps.assertIsPersonalDetailsFormOpen();
    }
}
