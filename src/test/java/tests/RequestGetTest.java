package tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.post.PostModelForResponse;
import models.user.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;

public class RequestGetTest extends BaseTest {

    private Gson gson = new Gson();

    @Test(priority = 1)
    public void getPosts() {
        MyLogger.logInfo("Start test for get posts");
        APIUtils.getRequest(ConfigManager.getTestDataString("getRequestPosts"));

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(APIUtils.isBodyJsonArrayFormat(), "List of posts returned not in JSON format");
        Assert.assertTrue(APIUtils.isAscendingIdOrder(), "List is not sorted by ID ascending");
    }

    @Test(priority = 2)
    public void getOnePost() {
        MyLogger.logInfo("Start test for get one post");
        APIUtils.getRequest(ConfigManager.getTestDataString("getRequestOnePost"));

        PostModelForResponse newPost = (PostModelForResponse) APIUtils.getObjectFromResponseBody(PostModelForResponse.class);

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(newPost.getUserId(), ConfigManager.getTestDataInt("userIdForGetRequestOnePost"),
                "Not correct post's userId"
        );
        Assert.assertEquals(newPost.getId(), ConfigManager.getTestDataInt("idForGetRequestOnePost"),
                "Not correct post's id"
        );
        Assert.assertNotNull(newPost.getTitle(), "Empty line");
        Assert.assertNotNull(newPost.getBody(), "Empty line");
    }

    @Test(priority = 3)
    public void getWrongPost() {
        MyLogger.logInfo("Start test for get wrong post");
        APIUtils.getRequest(ConfigManager.getTestDataString("getRequestWrongPost"));

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getBodyFromJsonObject(), new JsonObject(), "Response body isn't empty");
    }

    @Test(priority = 4)
    public void getUsers() {
        MyLogger.logInfo("Start test for get users");

        MyLogger.logInfo("Get local user data");
        User fifthUser = (User) ConfigManager.readData(ConfigManager.getTestDataString("currentUserPath"), User.class);

        int id = ConfigManager.getTestDataInt("userIdForGetRequestUsers");

        APIUtils.getRequest(ConfigManager.getTestDataString("getRequestUsers"));

        User newUser = APIUtils.getUserFromArrayById(id);

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(APIUtils.isBodyJsonArrayFormat(), "List of posts returned not in JSON format");
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

        ConfigManager.saveData(ConfigManager.getTestDataString("newUserPath"), gson.toJson(newUser));
    }

    @Test(priority = 5)
    public void getOneUser() {
        MyLogger.logInfo("Start test for get users");
        User fifthUser = (User) ConfigManager.readData(ConfigManager.getTestDataString("newUserPath"), User.class);

        APIUtils.getRequest(ConfigManager.getTestDataString("getRequestOneUser"));

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(gson.toJson(APIUtils.getObjectFromResponseBody(User.class)), gson.toJson(fifthUser),
                "Not correct fifth user's name"
        );

        ConfigManager.deleteFile(ConfigManager.getTestDataString("newUserPath"));
    }
}
