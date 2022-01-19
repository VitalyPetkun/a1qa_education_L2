package tests;

import com.google.gson.JsonObject;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;
import utils.MyLogger;

public class RequestGetTest extends BaseTest{

    @Test
    public void getPosts() {
        MyLogger.logInfo("Start test for get posts");
        APIUtils.getRequest("/posts");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertTrue(APIUtils.isBodyJsonArrayFormat(),"List of posts returned not in JSON format");
        Assert.assertTrue(APIUtils.isAscendingIdOrder(), "List is not sorted by ID ascending");
    }

    @Test
    public void getOnePost() {
        MyLogger.logInfo("Start test for get one post");
        APIUtils.getRequest("/posts/99");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getValue("userId"), 10, "UserId isn't '10'");
        Assert.assertEquals(APIUtils.getValue("id"), 99, "Id isn't '99'");
        Assert.assertNotEquals(APIUtils.getValue("title"), "", "Empty line");
        Assert.assertNotEquals(APIUtils.getValue("body"), "", "Empty line");
    }

    @Test
    public void getWrongPost() {
        MyLogger.logInfo("Start test for get wrong post");
        APIUtils.getRequest("/posts/150");

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getBodyFromJsonObject(), new JsonObject(), "Response body isn't empty");
    }
}
