package steps;

import org.testng.Assert;
import pages.WelcomePage;

public class WelcomePageSteps {

    private static final WelcomePage WELCOME_PAGE = new WelcomePage();

    public static void nextPageLnkClick() {
        WELCOME_PAGE.nextPageLnkClick();
    }

    public static void assertIsWelcomePageOpen() {
        Assert.assertTrue(WELCOME_PAGE.isDisplayed(), "Welcome page isn't open.");
    }
}
