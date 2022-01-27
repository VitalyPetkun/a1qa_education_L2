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

    private final String PATH_TEST_USER = "src\\test\\resources\\testUser.json";
    private final User user = PropertiesManager.readData(PATH_TEST_USER, User.class);

    @Test
    public void authorization() {
        SmartLogger.logStep("STEP №1: Navigate to welcome page");
        AqualityServices.getBrowser().goTo(PropertiesManager.getTestDataValue("vkUrl"));
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
        String postText = StringManager.generate(Integer.parseInt(PropertiesManager.getTestDataValue("postTextLength")));
        String request = "%s%s%s%s";
        response = VkApiUtils.doPost(String.format(
                request,
                METHOD.getPoint(PropertiesManager.getTestDataValue("wallPost")),
                PARAMS.getPoint(postText),
                TOKEN.getPoint(user.getToken()),
                VERSION.getPoint(PropertiesManager.getTestDataValue("wallPostVersionApi"))),
                postText
        );

        PostResponse postResponse = JsonManager.getObject(response.getBody(),PostResponse.class);
        Assert.assertNotEquals(postResponse.getResponse().getPost_id(),
                Integer.parseInt(PropertiesManager.getTestDataValue("wrongPostId")), "Post_id isn't correct"
        );
    }
}
