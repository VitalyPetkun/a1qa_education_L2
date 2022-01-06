package steps;

import org.testng.Assert;
import pages.WelcomePage;

public class WelcomePageSteps {
    private static final WelcomePage welcomePage = new WelcomePage();

    public static void linkNextPageClick() {
        welcomePage.linkNextPageClick();
    }

    public static void assertIsWelcomePageOpen() {
        Assert.assertTrue(welcomePage.isDisplayed(),"Welcome page isn't open.");
    }
}
