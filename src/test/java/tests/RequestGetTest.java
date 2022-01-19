package tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;
import utils.ConfigManager;
import utils.MyLogger;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RequestGetTest extends BaseTest {

    private Gson gson = new Gson();

    @Test(priority = 1)
    public void getPosts() {
        MyLogger.logInfo("Start test for get posts");
        APIUtils.getRequest("/posts");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(APIUtils.isBodyJsonArrayFormat(), "List of posts returned not in JSON format");
        Assert.assertTrue(APIUtils.isAscendingIdOrder(), "List is not sorted by ID ascending");
    }

    @Test(priority = 2)
    public void getOnePost() {
        MyLogger.logInfo("Start test for get one post");
        APIUtils.getRequest("/posts/99");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getObjectValue("userId"), 10, "UserId isn't '10'");
        Assert.assertEquals(APIUtils.getObjectValue("id"), 99, "Id isn't '99'");
        Assert.assertNotEquals(APIUtils.getObjectValue("title"), "", "Empty line");
        Assert.assertNotEquals(APIUtils.getObjectValue("body"), "", "Empty line");
    }

    @Test(priority = 3)
    public void getWrongPost() {
        MyLogger.logInfo("Start test for get wrong post");
        APIUtils.getRequest("/posts/150");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getBodyFromJsonObject(), new JsonObject(), "Response body isn't empty");
    }

    @Test(priority = 4)
    public void getUsers() {
        MyLogger.logInfo("Start test for get users");
        User fifthUser = null;
        int id = ConfigManager.getTestDataInt("fifthUserId");

        MyLogger.logInfo("Get local user data");
        try (JsonReader reader = new JsonReader(new FileReader(ConfigManager.getTestDataString("fifthUserPath")))) {
            fifthUser = gson.fromJson(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        APIUtils.getRequest("/users");

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

        this.saveData(gson.toJson(newUser));
    }

    @Test(priority = 5)
    public void getFifthUser() {
        MyLogger.logInfo("Start test for get users");
        User fifthUser = null;

        MyLogger.logInfo("Get save`s user data");
        try (JsonReader reader = new JsonReader(new FileReader(ConfigManager.getTestDataString("newUserPath")))) {
            fifthUser = gson.fromJson(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        APIUtils.getRequest("/users/5");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(gson.toJson(APIUtils.getUserFromResponseBody()), gson.toJson(fifthUser),
                "Not correct fifth user's name"
        );
    }

    private void saveData(String object) {
        MyLogger.logInfo("Save user data");
        try (FileWriter fileWriter = new FileWriter(ConfigManager.getTestDataString("newUserPath"))) {
            fileWriter.write(object);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
