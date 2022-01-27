package steps;

import org.testng.Assert;
import pages.NewsPage;
import pages.forms.SideBar;

public class NewsPageSteps {

    private static final SideBar SIDE_BAR = new SideBar();
    private static final NewsPage NEWS_PAGE = new NewsPage();

    public static void myProfileBtnClick() {
        SIDE_BAR.myProfileBtnClick();
    }

    public static void assertIsNewsPageOpen() {
        Assert.assertTrue(NEWS_PAGE.isDisplayed(), "News page isn't open.");
    }
}
