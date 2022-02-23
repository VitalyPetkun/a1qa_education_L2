package tests.ts01;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

public class HelpTest extends BaseTest {

    @Test
    public void actionsHelpForm() {
        SmartLogger.logStep(1, "Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("welcomePageURL"));
        WelcomePageSteps.assertIsOpenWelcomePage();

        SmartLogger.logStep(2, "Click link for navigate to login form");
        WelcomePageSteps.clickNextPageLnk();
        RegistrationPageSteps.assertIsOpenHelpForm();

        SmartLogger.logStep(3, "Hiding the help form");
        SmartLogger.logInfo("Click button for help form hidden");
        RegistrationPageSteps.clickSendToBottomBtn();
        SmartLogger.logInfo("Wait for help form hides");
        RegistrationPageSteps.waitForHelpFormHidden();
        RegistrationPageSteps.assertIsHiddenHelpForm();
    }
}
