package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.registrationsteps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.ConfigManager;

public class RegistrationTest extends BaseTest{
    private WelcomePageSteps welcomePageSteps;
    private RegistrationPageSteps registrationPageSteps;

    @Test
    public void registration() {
        welcomePageSteps = new WelcomePageSteps();
        registrationPageSteps = new RegistrationPageSteps();

        AqualityServices.getBrowser().goTo(ConfigManager.getTestDataString("welcomePageURL"));
        welcomePageSteps.isOpen();

        welcomePageSteps.clickLinkNextPage();
        registrationPageSteps.getLoginFormSteps().isOpen();

        registrationPageSteps.getLoginFormSteps().inputPassword();
        registrationPageSteps.getLoginFormSteps().inputEmail();
        registrationPageSteps.getLoginFormSteps().inputDomain();
        registrationPageSteps.getLoginFormSteps().selectDomainExtensions();
        registrationPageSteps.getLoginFormSteps().checkTermsAndConditions();
        registrationPageSteps.getLoginFormSteps().clickBtnNext();

        registrationPageSteps.getAvatarAndInterestsFormSteps().isOpen();
        registrationPageSteps.getAvatarAndInterestsFormSteps().checkThreeRandomInterests();
        registrationPageSteps.getAvatarAndInterestsFormSteps().uploadAvatarIcon();
        registrationPageSteps.getAvatarAndInterestsFormSteps().clickButtonNext();

        registrationPageSteps.getPersonalDetailsFormSteps().isOpen();
    }
}
