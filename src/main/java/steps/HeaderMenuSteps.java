package steps;

import org.testng.Assert;
import pages.forms.HeaderMenu;
import pages.forms.TopProfileMenu;

public class HeaderMenuSteps {

    private static final HeaderMenu HEADER_MENU = new HeaderMenu();
    private static final TopProfileMenu TOP_PROFILE_MENU = new TopProfileMenu();

    protected static void profileArrowCmbClick() {
        HEADER_MENU.profileArrowCmbClick();
    }

    protected static void signOutBtnClick() {
        HEADER_MENU.signOutBtnClick();
    }

    protected static void assertIsTopProfileMenuOpen() {
        Assert.assertTrue(TOP_PROFILE_MENU.isDisplayed(),"Top profile menu isn't open");
    }
}
