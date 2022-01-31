package tests;

import aquality.selenium.browser.AqualityServices;
import models.*;
import models.Comment.CommentResponse;
import models.Likes.LikesResponse;
import models.Post.PostResponse;
import org.testng.annotations.Test;
import steps.NewsPageSteps;
import steps.ProfilePageSteps;
import steps.WelcomePageSteps;
import utils.JsonManager;
import utils.PropertiesManager;
import utils.SmartLogger;
import utils.StringManager;
import utils.api.Response;
import utils.api.VkApiUtils;

import static services.VkEndPoints.*;

public class UserTest extends BaseTest {

    private final String VK_URL = PropertiesManager.getTestDataValue("vkUrl");
    private final String TEST_USER_PATH = "src\\test\\resources\\testUser.json";
    private final User USER = PropertiesManager.readData(TEST_USER_PATH, User.class);

    private Response response;
    private PostResponse postResponse;
    private CommentResponse commentResponse;
    private LikesResponse likesResponse;

    private String postText;
    private String editText;
    private String commentText;

    private int postId;
    private int commentId;

    @Test
    public void authorization() {
        SmartLogger.logStep("STEP №1: Navigate to welcome page");
        AqualityServices.getBrowser().goTo(VK_URL);
        WelcomePageSteps.assertIsWelcomePageOpen();

        SmartLogger.logStep("STEP №2: Authorization");
        SmartLogger.logInfo("Login input");
        WelcomePageSteps.loginTxtInput(USER.getLogin());
        SmartLogger.logInfo("Password input");
        WelcomePageSteps.passwordTxtInput(USER.getPassword());
        SmartLogger.logInfo("Click the 'Sign in' button");
        WelcomePageSteps.signInBtnClick();
        NewsPageSteps.assertIsNewsPageOpen();

        SmartLogger.logStep("STEP №3: Open profile page");
        NewsPageSteps.myProfileBtnClick();
        ProfilePageSteps.assertIsProfilePageOpen();

        SmartLogger.logStep("STEP №4: Create post");
        postText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallPost"), null),
                PARAM_MESSAGE.getPoint(postText, null),
                TOKEN.getPoint(USER.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );
        postResponse = JsonManager.getObject(response.getBody(), PostResponse.class);
        postId = postResponse.getResponse().getPost_id();

        SmartLogger.logStep("STEP №5: Checking post text and author");
        ProfilePageSteps.assertIsPostTextCorrect(postId, postText);
        ProfilePageSteps.assertIsPostAuthorCorrect(postId, USER.getId());

        SmartLogger.logStep("STEP №6: Edit post");
        editText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallEdit"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postResponse.getResponse().getPost_id()), null),
                PARAM_MESSAGE.getPoint(editText, null),
                PARAM_ATTACHMENT_PHOTO.getPoint(String.valueOf(USER.getId()), PropertiesManager.getTestDataValue("photoId")),
                TOKEN.getPoint(USER.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №7: Checking edit post title and photo");
        postResponse = JsonManager.getObject(response.getBody(), PostResponse.class);
        postId = postResponse.getResponse().getPost_id();
        ProfilePageSteps.assertIsPostPhotoCorrect(postId, USER.getId(),
                Integer.parseInt(PropertiesManager.getTestDataValue("photoId")));
        ProfilePageSteps.assertIsPostTextCorrect(postId, editText);

        SmartLogger.logStep("STEP №8: Create comment under post");
        commentText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("commentTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallCreateComment"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postResponse.getResponse().getPost_id()), null),
                PARAM_MESSAGE.getPoint(commentText, null),
                TOKEN.getPoint(USER.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №9: Checking comment");
        commentResponse = JsonManager.getObject(response.getBody(), CommentResponse.class);
        commentId = commentResponse.getResponse().getComment_id();
        ProfilePageSteps.showNextReplies(postId);
        ProfilePageSteps.assertIsCommentAuthorCorrect(postId, commentId, USER.getId());

        SmartLogger.logStep("STEP №10: Put post like");
        ProfilePageSteps.postLikeClick(postId);

        SmartLogger.logStep("STEP №11: Checking post like author");
        response = VkApiUtils.doGet(String.format(
                "%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("likesIsLiked"), null),
                PARAM_TYPE.getPoint(PropertiesManager.getTestDataValue("typePost"), null),
                PARAM_ITEM_ID.getPoint(String.valueOf(postId), null),
                TOKEN.getPoint(USER.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );
        likesResponse = JsonManager.getObject(response.getBody(), LikesResponse.class);
        ProfilePageSteps.assertIsLikeAuthor(likesResponse.getResponse().getLiked(),
                Integer.parseInt(PropertiesManager.getTestDataValue("liked")));

        SmartLogger.logStep("STEP №12: Delete post");
        VkApiUtils.doGet(String.format(
                "%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallDelete"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postId), null),
                TOKEN.getPoint(USER.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №13: Checking delete post");
        ProfilePageSteps.assertIsDeletePost(postId);
    }
}
