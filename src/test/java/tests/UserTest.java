package tests;

import aquality.selenium.browser.AqualityServices;
import models.User;
import org.testng.annotations.Test;
import steps.NewsPageSteps;
import steps.WelcomePageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

public class UserTest extends BaseTest{

    private final String PATH_TEST_USER = "src\\test\\resources\\testUser.json";

    @Test
    public void authorization() {
        SmartLogger.logInfo("Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("vkUri"));
        WelcomePageSteps.assertIsWelcomePageOpen();

        User user = PropertiesManager.readData(PATH_TEST_USER,User.class);
        SmartLogger.logInfo("Login input");
        WelcomePageSteps.loginTxtInput(user.getLogin());
        SmartLogger.logInfo("Password input");
        WelcomePageSteps.passwordTxtInput(user.getPassword());
        SmartLogger.logInfo("Click the 'Sign in' button");
        WelcomePageSteps.signInBtnClick();
        NewsPageSteps.assertIsNewsPageOpen();
    }
}
