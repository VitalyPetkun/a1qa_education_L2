package steps;

import org.testng.Assert;
import pages.WelcomePage;

public class WelcomePageSteps {

    private static final WelcomePage welcomePage = new WelcomePage();

    private WelcomePageSteps() {
    }

    public static void clickNextPageLnk() {
        welcomePage.clickNextPageLnk();
    }

    public static void assertIsOpenWelcomePage() {
        Assert.assertTrue(welcomePage.state().isDisplayed(), "Welcome page isn't open.");
    }
}
