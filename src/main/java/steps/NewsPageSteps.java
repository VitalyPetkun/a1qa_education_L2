package steps;

import org.testng.Assert;
import pages.NewsPage;
import pages.forms.SideBar;

public class NewsPageSteps {

    private static final SideBar sideBar = new SideBar();
    private static final NewsPage newsPage = new NewsPage();

    private NewsPageSteps() {
    }

    public static void myProfileBtnClick() {
        sideBar.myProfileBtnClick();
    }

    public static void assertIsNewsPageOpen() {
        Assert.assertTrue(newsPage.state().waitForDisplayed(), "News page isn't open.");
    }
}
