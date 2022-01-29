package steps;

import org.testng.Assert;
import pages.ProfilePage;

public class ProfilePageSteps {

    private static final ProfilePage PROFILE_PAGE = new ProfilePage();

    private static String getPostAuthor(int postId) {
        return PROFILE_PAGE.getPostAuthor(postId);
    }

    private static String getPostText(int postId) {
        return PROFILE_PAGE.getPostText(postId);
    }

    private static String getPostPhoto(int postId) {
        return PROFILE_PAGE.getPostPhoto(postId);
    }

    private static String getCommentAuthor(int postId, int commentId) {
        return PROFILE_PAGE.getCommentAuthor(postId, commentId);
    }

    public static void showNextReplies(int postId) {
        if (PROFILE_PAGE.isShowNextRepliesDisplayed(postId))
            PROFILE_PAGE.showNextRepliesClick(postId);
    }

    public static void postLikeClick(int postId) {
        PROFILE_PAGE.postLikeClick(postId);
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

    public static void assertIsCommentAuthorCorrect(int postId, int commentId, int userId) {
        Assert.assertEquals(getCommentAuthor(postId, commentId), userId,"Comment author id isn't correct");
    }

    public static void assertIsProfilePageOpen() {
        Assert.assertTrue(PROFILE_PAGE.isDisplayed(), "Profile page isn't open");
    }
}
