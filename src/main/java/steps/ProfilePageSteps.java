package steps;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import pages.ProfilePage;

public class ProfilePageSteps {

    private static final ProfilePage profilePage = new ProfilePage();

    private ProfilePageSteps() {
    }

    private static String getPostAuthor(int postId) {
        return profilePage.getPostAuthor(postId);
    }

    private static String getPostText(int postId) {
        return profilePage.getPostText(postId);
    }

    private static String getPostPhoto(int postId) {
        return profilePage.getPostPhoto(postId);
    }

    private static int getCommentAuthorId(int postId) {
        return Integer.parseInt(profilePage.getCommentAuthorId(postId));
    }

    private static boolean isPostDisplayed(int postId) {
        AqualityServices.getConditionalWait().waitFor(condition -> (!profilePage.isPostDisplayed(postId)));
        return profilePage.isPostDisplayed(postId);
    }

    public static void showNextReplies(int postId) {
        if (profilePage.isShowNextRepliesDisplayed(postId))
            profilePage.showNextRepliesClick();
    }

    public static void postLikeClick(int postId) {
        profilePage.postLikeClick(postId);
    }

    public static void assertIsPostTextCorrect(int postId, String postText) {
        Assert.assertEquals(getPostText(postId), postText, "Post text isn't correct");
    }

    public static void assertIsPostAuthorCorrect(int postId, int userId) {
        Assert.assertEquals(getPostAuthor(postId), String.valueOf(userId), "Author id isn't correct");
    }

    public static void assertIsPostPhotoCorrect(int postId, int userId, int photoId) {
        Assert.assertEquals(getPostPhoto(postId), String.format("%d_%d", userId, photoId), "Photo id isn't correct");
    }

    public static void assertIsCommentAuthorIdCorrect(int postId, int userId) {
        Assert.assertEquals(getCommentAuthorId(postId), userId, "Comment's author id isn't correct");
    }

    public static void assertIsAuthorLike(int liked, int comparedValue) {
        Assert.assertEquals(liked, comparedValue, "Author doesn't like post");
    }

    public static void assertIsPostExists(int postId) {
        Assert.assertFalse(isPostDisplayed(postId), "Post isn't delete");
    }

    public static void assertIsProfilePageOpen() {
        Assert.assertTrue(profilePage.state().waitForDisplayed(), "Profile page isn't open");
    }
}
