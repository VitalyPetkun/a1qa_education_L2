package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;

public class HelpTest extends BaseTest{

    @Test
    public void actionsHelpForm() {
        logger.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        logger.info("Click link for navigate to login form");
        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsHelpFormOpen();

        logger.info("Click button for help form hidden");
        RegistrationPageSteps.btnSendToBottomClick();
        logger.info("Wait for help form hides");
        RegistrationPageSteps.waitForHelpFormHidden();
        RegistrationPageSteps.assertIsHelpFormHidden();
    }
}
