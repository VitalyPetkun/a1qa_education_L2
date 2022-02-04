package steps;

import models.User;
import org.testng.Assert;
import pages.WelcomePage;

public class WelcomePageSteps {

    private static final WelcomePage welcomePage = new WelcomePage();

    private WelcomePageSteps() {
    }

    private static void loginTxtInput(String login) {
        welcomePage.loginTxtInput(login);
    }

    private static void passwordTxtInput(String password) {
        welcomePage.passwordTxtInput(password);
    }

    private static void signInBtnClick() {
        welcomePage.signInBtnClick();
    }

    public static void authorization(User user) {
        loginTxtInput(user.getLogin());
        passwordTxtInput(user.getPassword());
        signInBtnClick();
    }

    public static void assertIsWelcomePageOpen() {
        Assert.assertTrue(welcomePage.state().isDisplayed(), "Welcome page isn't open.");
    }
}
