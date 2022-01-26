package steps;

import org.testng.Assert;
import pages.WelcomePage;

public class WelcomePageSteps {

    private static final WelcomePage WELCOME_PAGE = new WelcomePage();

    public static void loginTxtInput(String login) {
        WELCOME_PAGE.loginTxtInput(login);
    }

    public static void passwordTxtInput(String password) {
        WELCOME_PAGE.passwordTxtInput(password);
    }

    public static void signInBtnClick() {
        WELCOME_PAGE.signInBtnClick();
    }

    public static void assertIsWelcomePageOpen() {
        Assert.assertTrue(WELCOME_PAGE.isDisplayed(), "Welcome page isn't open.");
    }
}
