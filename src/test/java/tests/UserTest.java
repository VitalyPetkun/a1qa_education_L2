package tests;

import aquality.selenium.browser.AqualityServices;
import models.PostResponse;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProfilePage;
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
        Response response;
        String postTitle = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength")));
        response = VkApiUtils.doPost(String.format(
                "%s%s%s%s",
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallPost")),
                PARAMS.getPoint(postTitle),
                TOKEN.getPoint(user.getToken()),
                VERSION.getPoint(PropertiesManager.getTestDataValue("wallPostVersionApi"))),
                postTitle
        );

        PostResponse postResponse = JsonManager.getObject(response.getBody(),PostResponse.class);
        Assert.assertNotEquals(postResponse.getResponse().getPost_id(),
                Integer.parseInt(PropertiesManager.getTestDataValue("wrongPostId")), "Post_id isn't correct"
        );

        SmartLogger.logStep("STEP №5: Checking post title and author");
        Assert.assertEquals(postTitle, ProfilePageSteps.getFirstPostTitle(), "Post title isn't correct");
        Assert.assertEquals(ProfilePageSteps.getFirstPostAuthor(),
                String.format("%s%s",VK_URL,MY_PROFILE.getPoint(null)),
                "User isn't correct");
    }
}
