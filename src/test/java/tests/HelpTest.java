package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;

public class HelpTest extends BaseTest {

    @Test
    public void actionsHelpForm() {
        LOGGER.info("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        LOGGER.info("Click link for navigate to login form");
        WelcomePageSteps.nextPageLnkClick();
        RegistrationPageSteps.assertIsHelpFormOpen();

        LOGGER.info("Click button for help form hidden");
        RegistrationPageSteps.sendToBottomBtnClick();
        LOGGER.info("Wait for help form hides");
        RegistrationPageSteps.waitForHelpFormHidden();
        RegistrationPageSteps.assertIsHelpFormHidden();
    }
}
