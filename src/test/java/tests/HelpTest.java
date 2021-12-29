package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.registrationsteps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;

public class HelpTest extends BaseTest{
    private WelcomePageSteps welcomePageSteps;
    private RegistrationPageSteps registrationPageSteps;

    @Test
    public void actionsHelpForm() {
        welcomePageSteps = new WelcomePageSteps();
        registrationPageSteps = new RegistrationPageSteps();

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        welcomePageSteps.isOpen();

        welcomePageSteps.clickLinkNextPage();
        registrationPageSteps.getHelpFormSteps().clickBtnSendToBottom();
        registrationPageSteps.getHelpFormSteps().isClose();
    }
}
