package tests;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;

public class RequestGetTest {

    @Test
    public void getAllPosts() {

        APIUtils.requestGet("/posts");
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getBodyFromJson(), String.class, "List of posts returned not in JSON format");
    }

    @Test
    public void get_99_Post() {

        APIUtils.requestGet("/posts/99");
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getValue("userId"), 10, "UserId isn't '10'");
        Assert.assertEquals(APIUtils.getValue("id"), 99, "Id isn't '99'");
        Assert.assertNotEquals(APIUtils.getValue("title"), "", "Empty line");
        Assert.assertNotEquals(APIUtils.getValue("body"), "", "Empty line");
    }

    @Test
    public void getWrongPost() {

        APIUtils.requestGet("/posts/150");
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Wrong status code returned");
        Assert.assertEquals(APIUtils.getBodyFromJson(), "\"{}\"", "Response body isn't empty");
    }
}
