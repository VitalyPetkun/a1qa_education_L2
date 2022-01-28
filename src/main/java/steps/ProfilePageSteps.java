package steps;

import org.testng.Assert;
import pages.ProfilePage;

public class ProfilePageSteps {

    private static final ProfilePage PROFILE_PAGE = new ProfilePage();

    public static String getFirstPostAuthor() {
        return PROFILE_PAGE.getFirstPostAuthor();
    }

    public static String getFirstPostText() {
        return PROFILE_PAGE.getFirstPostText();
    }

    public static String getFirstPostPhoto() {
        return PROFILE_PAGE.getFirstPostPhoto();
    }

    public static void assertIsProfilePageOpen() {
        Assert.assertTrue(PROFILE_PAGE.isDisplayed(),"Profile page isn't open");
    }
}
