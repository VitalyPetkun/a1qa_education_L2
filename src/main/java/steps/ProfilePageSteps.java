package steps;

import org.testng.Assert;
import pages.ProfilePage;

public class ProfilePageSteps {

    private static final ProfilePage PROFILE_PAGE = new ProfilePage();

    public static String getPostAuthor(String postId) {
        return PROFILE_PAGE.getPostAuthor(postId);
    }

    public static String getPostText(String postId) {
        return PROFILE_PAGE.getPostText(postId);
    }

    public static String getPostPhoto(String postId) {
        return PROFILE_PAGE.getPostPhoto(postId);
    }

    public static String getPostCommentAuthor(String postId, String commentId) {
        return PROFILE_PAGE.getPostCommentAuthor(postId, commentId);
    }

    public static void showNextReplies(String postId) {
        if(PROFILE_PAGE.isShowNextRepliesDisplayed(postId))
            PROFILE_PAGE.showNextRepliesClick(postId);
    }

    public static void assertIsProfilePageOpen() {
        Assert.assertTrue(PROFILE_PAGE.isDisplayed(),"Profile page isn't open");
    }
}
