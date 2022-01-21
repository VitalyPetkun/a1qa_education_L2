package tests;

import models.post.PostModelForRequest;
import models.post.PostModelForResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.API.APIUtils;
import utils.API.Response;
import utils.ConfigManager;
import utils.GeneratingRandomString;
import utils.JsonConverter;
import utils.Logger;
import static services.EndPointsJSONPlaceholder.POSTS;

public class RequestPostTest extends BaseTest {

    @Test
    public void postMyPost() {
        Logger.logStep("POST request for creating 'post'");

        Logger.logInfo("Creat my post");
        PostModelForRequest myPost = new PostModelForRequest();

        myPost.setUserId(Integer.parseInt(ConfigManager.getTestDataValue("userIdForPostRequestPosts")));
        myPost.setTitle(GeneratingRandomString.generate((Integer.parseInt(ConfigManager.
                getTestDataValue("randomTitleLengthForGetRequestOnePost")))));
        myPost.setBody(GeneratingRandomString.generate((Integer.parseInt(ConfigManager.
                getTestDataValue("randomBodyLengthForGetRequestOnePost")))));

        APIUtils.doPost(POSTS.getPoint(), JsonConverter.convertFromJson(myPost));

        PostModelForResponse newPost = (PostModelForResponse) JsonConverter.
                convertToJson(Response.getBody(),PostModelForResponse.class);

        Assert.assertEquals(Response.getStatus(), HttpStatus.SC_CREATED, "Wrong status code returned");
        Assert.assertEquals(newPost.getUserId(), Integer.parseInt(ConfigManager.
                        getTestDataValue("userIdForPostRequestPosts")),"Not correct post's userId"
        );
        Assert.assertNotEquals(newPost.getId(), Integer.parseInt(ConfigManager.
                        getTestDataValue("emptyIdForPostRequestPosts")),"Empty id"
        );
        Assert.assertEquals(newPost.getTitle(), myPost.getTitle(), "Not correct post's title");
        Assert.assertEquals(newPost.getBody(), myPost.getBody(), "Not correct post's body");
    }
}
