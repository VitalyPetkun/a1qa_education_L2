package steps;

import org.testng.Assert;
import pages.NewsPage;
import pages.forms.SideBar;

public class NewsPageSteps {

    private static final HeaderMenuSteps HEADER_MENU_STEPS = new HeaderMenuSteps();
    private static final SideBar SIDE_BAR = new SideBar();
    private static final NewsPage NEWS_PAGE = new NewsPage();

    public static void profileArrowCmbClick() {
        HEADER_MENU_STEPS.profileArrowCmbClick();
    }

    public static void signOutBtnClick() {
        HEADER_MENU_STEPS.signOutBtnClick();
    }

    public static void assertIsSideBarOpen() {
        Assert.assertTrue(SIDE_BAR.isDisplayed(),"Side bar isn't open");
    }

    public static void assertIsNewsPageOpen() {
        Assert.assertTrue(NEWS_PAGE.isDisplayed(), "News page isn't open.");
    }
}
