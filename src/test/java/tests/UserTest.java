package tests;

import aquality.selenium.browser.AqualityServices;
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
        String postText;
        PostResponse postResponse;
        String editText;
        String comment;

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

        postResponse = JsonManager.getObject(response.getBody(),PostResponse.class);
        Assert.assertNotEquals(postResponse.getResponse().getPost_id(),
                Integer.parseInt(PropertiesManager.getTestDataValue("wrongPostId")), "Post_id isn't correct");

        SmartLogger.logStep("STEP №5: Checking post text and author");
        Assert.assertEquals(postText, ProfilePageSteps.getFirstPostText(), "Post text isn't correct");
        Assert.assertEquals(ProfilePageSteps.getFirstPostAuthor(), String.valueOf(user.getId()), "User isn't correct");

        SmartLogger.logStep("STEP №6: Edit post");
        editText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallEdit"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postResponse.getResponse().getPost_id()), null),
                PARAM_MESSAGE.getPoint(editText, null),
                PARAM_ATTACHMENT_PHOTO.getPoint(String.valueOf(user.getId()),PropertiesManager.getTestDataValue("idPhoto")),
                TOKEN.getPoint(user.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №7: Checking edit post title and photo");
        Assert.assertEquals(editText, ProfilePageSteps.getFirstPostText(), "Post title isn't correct");
        Assert.assertEquals(ProfilePageSteps.getFirstPostPhoto(),
                String.format("%s_%s", user.getId(), PropertiesManager.getTestDataValue("idPhoto")),
                "Id photo isn't correct");

        SmartLogger.logStep("STEP №8: Create comment under post");
        comment = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("commentTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallCreateComment"), null),
                PARAM_POST_ID.getPoint(String.valueOf(postResponse.getResponse().getPost_id()), null),
                PARAM_MESSAGE.getPoint(comment, null),
                TOKEN.getPoint(user.getToken(), null),
                VERSION.getPoint(PropertiesManager.getTestDataValue("versionApi"), null))
        );

        SmartLogger.logStep("STEP №9: Checking comment");

    }
}
