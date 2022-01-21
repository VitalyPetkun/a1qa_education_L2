package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import utils.API.APIUtils;
import models.post.PostModelForResponse;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.API.Response;
import static services.EndPointsJSONPlaceholder.*;
import static services.PathToFiles.*;

public class RequestGetTest extends BaseTest {

    @Test(priority = 1)
    public void getPosts() {
        Logger.logStep("GET request for found all 'posts'");
        APIUtils.doGet(POSTS.getPoint());

        Assert.assertEquals(Response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(JsonArray.class.equals(JsonConverter.convertToJson(Response.getBody(),JsonArray.class).getClass()),
                "List of posts returned not in JSON format");
        Assert.assertTrue(CheckingUserList.isAscendingUserIdOrder(JsonConverter.convertToPostList(Response.getBody())),
                "List is not sorted by ID ascending");
    }

    @Test(priority = 2)
    public void getOnePost() {
        Logger.logStep("GET request for found 'post' №99");
        APIUtils.doGet(NINETIETH_POST.getPoint());

        PostModelForResponse newPost = (PostModelForResponse) JsonConverter.
                convertToJson(Response.getBody(),PostModelForResponse.class);

        Assert.assertEquals(Response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(newPost.getUserId(), Integer.parseInt(ConfigManager.
                        getTestDataValue("userIdForGetRequestOnePost")),"Not correct post's userId"
        );
        Assert.assertEquals(newPost.getId(), Integer.parseInt(ConfigManager.
                        getTestDataValue("idForGetRequestOnePost")),"Not correct post's id"
        );
        Assert.assertNotNull(newPost.getTitle(), "Empty line");
        Assert.assertNotNull(newPost.getBody(), "Empty line");
    }

    @Test(priority = 3)
    public void getWrongPost() {
        Logger.logStep("GET request for found 'posts' №150");
        APIUtils.doGet(WRONG_POST.getPoint());

        Assert.assertEquals(Response.getStatus(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");
        Assert.assertEquals(JsonConverter.convertToJson(Response.getBody(), JsonObject.class), new JsonObject(),
                "Response body isn't empty");
    }

    @Test(priority = 4)
    public void getUsers() {
        Logger.logStep("GET request for found all users");
        Logger.logInfo("Get local user data");
        User fifthUser = (User) ConfigManager.readData(CURRENT_USER.getPath(), User.class);

        int id = Integer.parseInt(ConfigManager.getTestDataValue("userIdForGetRequestUsers"));

        APIUtils.doGet(USERS.getPoint());

        User newUser = JsonConverter.convertToUserList(Response.getBody()).get(id-1);

        Assert.assertEquals(Response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(JsonArray.class.equals(JsonConverter.convertToJson(Response.getBody(),JsonArray.class).getClass()),
                "List of posts returned not in JSON format");
        Assert.assertEquals(newUser.getName(), fifthUser.getName(), "Not correct fifth user's name");
        Assert.assertEquals(newUser.getUsername(), fifthUser.getUsername(), "Not correct fifth user's username");
        Assert.assertEquals(newUser.getEmail(), fifthUser.getEmail(), "Not correct fifth user's email");
        Assert.assertEquals(newUser.getAddress().getStreet(), fifthUser.getAddress().getStreet(),
                "Not correct fifth user's street"
        );
        Assert.assertEquals(newUser.getAddress().getSuite(), fifthUser.getAddress().getSuite(),
                "Not correct fifth user's suite"
        );
        Assert.assertEquals(newUser.getAddress().getCity(), fifthUser.getAddress().getCity(),
                "Not correct fifth user's city"
        );
        Assert.assertEquals(newUser.getAddress().getZipcode(), fifthUser.getAddress().getZipcode(),
                "Not correct fifth user's zipcode"
        );
        Assert.assertEquals(newUser.getAddress().getGeo().getLat(), fifthUser.getAddress().getGeo().getLat(),
                "Not correct fifth user's lat"
        );
        Assert.assertEquals(newUser.getAddress().getGeo().getLng(), fifthUser.getAddress().getGeo().getLng(),
                "Not correct fifth user's lng"
        );
        Assert.assertEquals(newUser.getPhone(), fifthUser.getPhone(),
                "Not correct fifth user's phone"
        );
        Assert.assertEquals(newUser.getWebsite(), fifthUser.getWebsite(),
                "Not correct fifth user's website"
        );
        Assert.assertEquals(newUser.getCompany().getName(), fifthUser.getCompany().getName(),
                "Not correct fifth user's name company"
        );
        Assert.assertEquals(newUser.getCompany().getCatchPhrase(), fifthUser.getCompany().getCatchPhrase(),
                "Not correct fifth user's catch phrase"
        );
        Assert.assertEquals(newUser.getCompany().getBs(), fifthUser.getCompany().getBs(),
                "Not correct fifth user's bs"
        );

        ConfigManager.saveData(NEW_USER.getPath(), JsonConverter.convertFromJson(newUser));
    }

    @Test(priority = 5)
    public void getOneUser() {
        Logger.logStep("GET request for found fifth users");
        User fifthUser = (User) ConfigManager.readData(NEW_USER.getPath(), User.class);

        APIUtils.doGet(FIFTH_USER.getPoint());

        Assert.assertEquals(Response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(JsonConverter.convertFromJson(JsonConverter.convertToJson(Response.getBody(), User.class)),
                JsonConverter.convertFromJson(fifthUser),"Not correct fifth user's name"
        );

        ConfigManager.deleteFile(NEW_USER.getPath());
    }
}

