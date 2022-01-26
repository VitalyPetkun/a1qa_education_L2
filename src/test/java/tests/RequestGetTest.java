package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import utils.api.APIUtils;
import models.post.PostModelForResponse;
import models.user.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.api.Response;

import java.util.List;
import static services.EndPointsJSONPlaceholder.*;
import static services.PathToFiles.*;

public class RequestGetTest extends BaseTest {

    private Response response;

    @Test(priority = 1)
    public void foundAllPosts() {
        SmartLogger.logStep("STEP №1: GET request for found all 'posts'");
        response = APIUtils.doGet(POSTS.getPoint());
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");

        JsonArray responseBody = JsonConverter.getObject(response.getBody(), JsonArray.class);
        Assert.assertTrue(JsonArray.class.equals(responseBody.getClass()), "List of posts returned not in JSON format");

        List<PostModelForResponse> posts = JsonConverter.getList(response.getBody(), PostModelForResponse.class);
        Assert.assertTrue(CheckingUserList.isAscendingUserIdOrder(posts), "List is not sorted by ID ascending");
    }

    @Test(priority = 2)
    public void foundNinetiethPost() {
        SmartLogger.logStep("STEP №2: GET request for found 'post' №99");
        PostModelForResponse expectedPost = ConfigManager.readData(EXPECTED_POST.getPath(), PostModelForResponse.class);

        response = APIUtils.doGet(POST.getChangedPoint(ConfigManager.getTestDataValue("ninetiethPostNumber")));
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");

        PostModelForResponse actualPost = JsonConverter.getObject(response.getBody(), PostModelForResponse.class);
        Assert.assertEquals(JsonConverter.getString(actualPost), JsonConverter.getString(expectedPost),
                "Not correct actual post"
        );
    }

    @Test(priority = 3)
    public void foundWrongPost() {
        SmartLogger.logStep("STEP №3: GET request for found 'posts' №150");
        response = APIUtils.doGet(POST.getChangedPoint(ConfigManager.getTestDataValue("wrongPostNumber")));
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");

        JsonObject responseBody = JsonConverter.getObject(response.getBody(), JsonObject.class);
        Assert.assertEquals(responseBody, new JsonObject(), "Response body isn't empty");
    }

    @Test(priority = 4)
    public void foundAllUsers() {
        SmartLogger.logStep("STEP №5: GET request for found all users");
        SmartLogger.logInfo("Get expected user data");
        User expectedUser = ConfigManager.readData(EXPECTED_USER.getPath(), User.class);

        response = APIUtils.doGet(USERS.getPoint());

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");

        JsonArray responseBody = JsonConverter.getObject(response.getBody(), JsonArray.class);
        Assert.assertTrue(JsonArray.class.equals(responseBody.getClass()),
                "List of posts returned not in JSON format"
        );

        List<User> users = JsonConverter.getList(response.getBody(), User.class);
        int id = Integer.parseInt(ConfigManager.getTestDataValue("userIdForGetRequestUsers"));
        User actualUser = users.get(id - 1);
        Assert.assertEquals(JsonConverter.getString(actualUser), JsonConverter.getString(expectedUser),
                "Not correct actual user"
        );

        ConfigManager.saveData(ACTUAL_USER.getPath(), JsonConverter.getString(actualUser));
    }

    @Test(priority = 5)
    public void foundFifthUser() {
        SmartLogger.logStep("STEP №6: GET request for found actual user");
        User expectedUser = ConfigManager.readData(ACTUAL_USER.getPath(), User.class);

        response = APIUtils.doGet(USER.getChangedPoint(ConfigManager.getTestDataValue("actualUserNumber")));
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");

        User actualUser = JsonConverter.getObject(response.getBody(), User.class);
        Assert.assertEquals(JsonConverter.getString(actualUser), JsonConverter.getString(expectedUser),
                "Not correct actual user"
        );

        ConfigManager.deleteFile(ACTUAL_USER.getPath());
    }
}

