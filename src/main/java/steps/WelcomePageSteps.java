package steps;

import org.testng.Assert;
import pages.WelcomePage;

public class WelcomePageSteps extends WelcomePage {
    public WelcomePageSteps() {
        super();
    }

    public void clickLinkNextPage() {
        super.getElementFactory().getLink(XPATH_LINK_NEXT_PAGE, "Link next page").click();
    }

    public void isOpen() {
        Assert.assertTrue(this.isDisplayed());
    }
}
