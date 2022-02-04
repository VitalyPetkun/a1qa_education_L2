package tests;

import aquality.selenium.browser.AqualityServices;
import models.*;
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

import static services.VkParameters.*;

public class UserTest extends BaseTest {

    private final String TEST_USER_PATH = "src\\test\\resources\\testUser.json";
    private final String VK_URL = PropertiesManager.getTestDataValue("vkUrl");
    private final int POST_TEXT_LENGTH = Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength"));
    private final int COMMENT_TEXT_LENGTH = Integer.parseInt(PropertiesManager.getTestDataValue("commentTextLength"));
    private final User user = PropertiesManager.readData(TEST_USER_PATH, User.class);

    private Response response;
    private PostResponse postResponse;
    private LikesResponse likesResponse;

    private String postText;
    private String editText;
    private String commentText;

    private int postId;

    @Test
    public void authorization() {
        SmartLogger.logStep(1, "Navigate to welcome page");
        AqualityServices.getBrowser().goTo(VK_URL);
        WelcomePageSteps.assertIsWelcomePageOpen();

        SmartLogger.logStep(2, "Authorization");
        WelcomePageSteps.authorization(user);
        NewsPageSteps.assertIsNewsPageOpen();

        SmartLogger.logStep(3, "Open profile page");
        NewsPageSteps.myProfileBtnClick();
        ProfilePageSteps.assertIsProfilePageOpen();

        SmartLogger.logStep(4, "Create post");
        postText = StringManager.generate(POST_TEXT_LENGTH);
        response = VkApiUtils.createPost(postText, user.getToken());
        postResponse = JsonManager.getObject(response.getBody(), PostResponse.class);
        postId = postResponse.getResponse().getPost_id();

        SmartLogger.logStep(5, "Checking post text and author");
        ProfilePageSteps.assertIsPostTextCorrect(postId, postText);
        ProfilePageSteps.assertIsPostAuthorCorrect(postId, user.getId());

        SmartLogger.logStep(6, "Edit post");
        editText = StringManager.generate(POST_TEXT_LENGTH);
        response = VkApiUtils.editPost(postId, editText, user.getId(), PHOTO_ID.getPhotoId(), user.getToken());

        SmartLogger.logStep(7, "Checking edit post title and photo");
        postResponse = JsonManager.getObject(response.getBody(), PostResponse.class);
        postId = postResponse.getResponse().getPost_id();
        ProfilePageSteps.assertIsPostPhotoCorrect(postId, user.getId(), PHOTO_ID.getPhotoId());
        ProfilePageSteps.assertIsPostTextCorrect(postId, editText);

        SmartLogger.logStep(8, "Create comment under post");
        commentText = StringManager.generate(COMMENT_TEXT_LENGTH);
        response = VkApiUtils.createComment(postId, commentText, user.getToken());

        SmartLogger.logStep(9, "Checking comment");
        ProfilePageSteps.showNextReplies(postId);
        ProfilePageSteps.assertIsCommentAuthorIdCorrect(postId, user.getId());

        SmartLogger.logStep(10, "Put post like");
        ProfilePageSteps.postLikeClick(postId);

        SmartLogger.logStep(11, "Checking post like author");
        response = VkApiUtils.isUserLiked(OBJECT_TYPE.getObjectType(), postId, user.getToken());
        likesResponse = JsonManager.getObject(response.getBody(), LikesResponse.class);
        ProfilePageSteps.assertIsAuthorLike(likesResponse.getResponse().getLiked(), LIKED.getLiked());

        SmartLogger.logStep(12, "Delete post");
        VkApiUtils.deletePost(postId, user.getToken());

        SmartLogger.logStep(13, "Checking delete post");
        ProfilePageSteps.assertIsPostExists(postId);
    }
}
