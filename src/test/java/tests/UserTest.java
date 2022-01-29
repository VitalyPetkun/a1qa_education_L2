package tests;

import aquality.selenium.browser.AqualityServices;
import models.CommentResponse;
import models.PostResponse;
import models.User;
import org.testng.Assert;
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
    private final String PATH_TEST_USER = "src\\test\\resources\\testUser.json";
    private final User user = PropertiesManager.readData(PATH_TEST_USER, User.class);

    @Test
    public void authorization() {
        Response response;
        PostResponse postResponse;
        CommentResponse commentResponse;

        String postText;
        String editText;
        String commentText;

        int postId;
        int commentId;

        SmartLogger.logStep("STEP №1: Navigate to welcome page");
        AqualityServices.getBrowser().goTo(VK_URL);
        WelcomePageSteps.assertIsWelcomePageOpen();

        SmartLogger.logStep("STEP №2: Authorization");
        SmartLogger.logInfo("Login input");
        WelcomePageSteps.loginTxtInput(user.getLogin());
        SmartLogger.logInfo("Password input");
        WelcomePageSteps.passwordTxtInput(user.getPassword());
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
                TOKEN.getPoint(user.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );
        postResponse = JsonManager.getObject(response.getBody(), PostResponse.class);
        postId = postResponse.getResponse().getPost_id();

        SmartLogger.logStep("STEP №5: Checking post text and author");
        ProfilePageSteps.assertIsPostTextCorrect(postId, postText);
        ProfilePageSteps.assertIsPostAuthorCorrect(postId, user.getId());

        SmartLogger.logStep("STEP №6: Edit post");
        editText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallEdit"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postResponse.getResponse().getPost_id()), null),
                PARAM_MESSAGE.getPoint(editText, null),
                PARAM_ATTACHMENT_PHOTO.getPoint(String.valueOf(user.getId()), PropertiesManager.getTestDataValue("photoId")),
                TOKEN.getPoint(user.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №7: Checking edit post title and photo");
        postResponse = JsonManager.getObject(response.getBody(), PostResponse.class);
        postId = postResponse.getResponse().getPost_id();
        ProfilePageSteps.assertIsPostTextCorrect(postId, editText);
        ProfilePageSteps.assertIsPostPhotoCorrect(postId, user.getId(),
                Integer.parseInt(PropertiesManager.getTestDataValue("photoId")));

        SmartLogger.logStep("STEP №8: Create comment under post");
        commentText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("commentTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallCreateComment"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postResponse.getResponse().getPost_id()), null),
                PARAM_MESSAGE.getPoint(commentText, null),
                TOKEN.getPoint(user.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №9: Checking comment");
        commentResponse = JsonManager.getObject(response.getBody(), CommentResponse.class);
        commentId = commentResponse.getResponse().getComment_id();
        ProfilePageSteps.showNextReplies(postId);
        ProfilePageSteps.assertIsCommentAuthorCorrect(postId, commentId, user.getId());

        SmartLogger.logStep("STEP №10: Put post like");
        ProfilePageSteps.postLikeClick(postId);


    }
}
