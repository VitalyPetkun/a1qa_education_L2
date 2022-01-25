package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import utils.api.APIUtils;
import models.post.PostModelForResponse;
import models.user.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.api.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static services.EndPointsJSONPlaceholder.*;
import static services.PathToFiles.*;

public class RequestGetTest extends BaseTest {

    private Response response;

    @Test(priority = 1)
    public void foundAllPosts() {
        SmartLogger.logStep("STEP №1: GET request for found all 'posts'");
        response = APIUtils.doGet(POSTS.getPoint());

        JsonArray responseBody = (JsonArray) JsonConverter.convertToJson(response.getBody(), JsonArray.class);

        Type listType = new TypeToken<ArrayList<PostModelForResponse>>() {}.getType();

        List<PostModelForResponse> posts = (ArrayList<PostModelForResponse>)
                JsonConverter.convertToJson(response.getBody(), listType);

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(JsonArray.class.equals(responseBody.getClass()), "List of posts returned not in JSON format");
        Assert.assertTrue(CheckingUserList.isAscendingUserIdOrder(posts), "List is not sorted by ID ascending");
    }

    @Test(priority = 2)
    public void foundNinetiethPost() {
        SmartLogger.logStep("STEP №2: GET request for found 'post' №99");
        PostModelForResponse expectedPost = (PostModelForResponse) ConfigManager.readData(EXPECTED_POST.getPath(),
                PostModelForResponse.class
        );

        response = APIUtils.doGet(POST.getChangedPoint(ConfigManager.getTestDataValue("ninetiethPostNumber")));

        PostModelForResponse actualPost = (PostModelForResponse) JsonConverter.
                convertToJson(response.getBody(), PostModelForResponse.class);

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(JsonConverter.convertToString(actualPost), JsonConverter.convertToString(expectedPost),
                "Not correct actual post"
        );
    }

    @Test(priority = 3)
    public void foundWrongPost() {
        SmartLogger.logStep("STEP №3: GET request for found 'posts' №150");
        response = APIUtils.doGet(POST.getChangedPoint(ConfigManager.getTestDataValue("wrongPostNumber")));

        JsonObject responseBody = (JsonObject) JsonConverter.convertToJson(response.getBody(), JsonObject.class);

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");
        Assert.assertEquals(responseBody, new JsonObject(), "Response body isn't empty");
    }

    @Test(priority = 4)
    public void foundAllUsers() {
        SmartLogger.logStep("STEP №5: GET request for found all users");
        SmartLogger.logInfo("Get expected user data");
        User expectedUser = (User) ConfigManager.readData(EXPECTED_USER.getPath(), User.class);

        response = APIUtils.doGet(USERS.getPoint());

        JsonArray responseBody = (JsonArray) JsonConverter.convertToJson(response.getBody(), JsonArray.class);

        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();
        List<User> users = (ArrayList<User>) JsonConverter.convertToJson(response.getBody(), listType);

        int id = Integer.parseInt(ConfigManager.getTestDataValue("userIdForGetRequestUsers"));
        User actualUser = users.get(id - 1);

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(JsonArray.class.equals(responseBody.getClass()),
                "List of posts returned not in JSON format"
        );
        Assert.assertEquals(JsonConverter.convertToString(actualUser), JsonConverter.convertToString(expectedUser),
                "Not correct actual user"
        );

        ConfigManager.saveData(ACTUAL_USER.getPath(), JsonConverter.convertToString(actualUser));
    }

    @Test(priority = 5)
    public void foundFifthUser() {
        SmartLogger.logStep("STEP №6: GET request for found actual user");
        User expectedUser = (User) ConfigManager.readData(ACTUAL_USER.getPath(), User.class);

        response = APIUtils.doGet(USER.getChangedPoint(ConfigManager.getTestDataValue("actualUserNumber")));

        User actualUser = (User) JsonConverter.convertToJson(response.getBody(), User.class);

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(JsonConverter.convertToString(actualUser), JsonConverter.convertToString(expectedUser),
                "Not correct actual user"
        );

        ConfigManager.deleteFile(ACTUAL_USER.getPath());
    }
}

