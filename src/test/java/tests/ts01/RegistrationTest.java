package tests.ts01;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.RegistrationPageSteps;
import steps.WelcomePageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

public class RegistrationTest extends BaseTest {

    @Test
    public void registration() {
        SmartLogger.logStep(1, "Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("welcomePageURL"));
        WelcomePageSteps.assertIsOpenWelcomePage();

        SmartLogger.logStep(2, "Click link for navigate to login form");
        WelcomePageSteps.clickNextPageLnk();
        RegistrationPageSteps.assertIsOpenLoginForm();

        SmartLogger.logStep(3, "Filling in the data on the login form");
        SmartLogger.logInfo("Password input");
        RegistrationPageSteps.inputPasswordTxt();
        SmartLogger.logInfo("E-mail input");
        RegistrationPageSteps.inputEmailTxt();
        SmartLogger.logInfo("Domain input");
        RegistrationPageSteps.inputDmainTxt();
        SmartLogger.logInfo("Domain extensions select");
        RegistrationPageSteps.clickDomainExtensionsItem();
        SmartLogger.logInfo("Accept terms and conditions of use");
        RegistrationPageSteps.checkTermsAndConditionsChk();
        SmartLogger.logInfo("Click button for navigate to avatar and interests form");
        RegistrationPageSteps.clickLoginFormNextBtn();
        RegistrationPageSteps.assertIsOpenAvatarAndInterestsForm();

        SmartLogger.logStep(4, "Filling in the data on the interests form");
        SmartLogger.logInfo("Upload avatar icon");
        RegistrationPageSteps.uploadAvatarIcon();
        SmartLogger.logInfo("Three random interests select");
        RegistrationPageSteps.selectThreeRandomInterests();
        SmartLogger.logInfo("Click button for navigate to personal details form");
        RegistrationPageSteps.clickAvatarAndInterestsFormNextBtn();
        RegistrationPageSteps.assertIsOpenPersonalDetailsForm();
    }
}
