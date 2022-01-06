package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;

public class HelpTest extends BaseTest{

    @Test
    public void actionsHelpForm() {

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        WelcomePageSteps.linkNextPageClick();
        RegistrationPageSteps.assertIsHelpFormOpen();

        RegistrationPageSteps.btnSendToBottomClick();
        RegistrationPageSteps.waitForHelpFormHidden();
        RegistrationPageSteps.assertIsHelpFormHidden();
    }
}
