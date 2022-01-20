package tests;

import models.post.PostModelForRequest;
import models.post.PostModelForResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;
import utils.ConfigManager;
import utils.MyLogger;

public class RequestPostTest extends BaseTest {

    @Test
    public void postMyPost() {
        MyLogger.logInfo("Start test for post my post");

        MyLogger.logInfo("Creat my post");
        PostModelForRequest myPost = new PostModelForRequest();

        myPost.setUserId(ConfigManager.getTestDataInt("userIdForPostRequestPosts"));
        String randomTitle = myPost.setRandomTitle();
        String randomBody = myPost.setRandomBody();

        APIUtils.postRequest(ConfigManager.getTestDataString("postRequestPosts"), APIUtils.setBodyToJson(myPost));

        PostModelForResponse newPost = (PostModelForResponse) APIUtils.getObjectFromResponseBody(PostModelForResponse.class);

        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_CREATED, "Wrong status code returned");
        Assert.assertEquals(newPost.getUserId(), ConfigManager.getTestDataInt("userIdForPostRequestPosts"),
                "Not correct post's userId"
        );
        Assert.assertNotEquals(newPost.getId(), ConfigManager.getTestDataInt("emptyIdForPostRequestPosts"),
                "Empty id"
        );
        Assert.assertEquals(newPost.getTitle(), randomTitle, "Not correct post's title");
        Assert.assertEquals(newPost.getBody(), randomBody, "Not correct post's body");
    }
}
