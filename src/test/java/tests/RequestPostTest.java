package tests;

import models.post.PostModelForRequest;
import models.post.PostModelForResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.api.APIUtils;
import utils.api.Response;
import utils.ConfigManager;
import utils.GeneratingRandomString;
import utils.JsonConverter;
import utils.SmartLogger;
import static services.EndPointsJSONPlaceholder.POSTS;

public class RequestPostTest extends BaseTest {

    private Response response;

    @Test
    public void post() {
        SmartLogger.logStep("STEP №4: POST request for creating 'post'");
        SmartLogger.logInfo("Creat post");
        PostModelForRequest expectedPost = new PostModelForRequest();

        expectedPost.setUserId(Integer.parseInt(ConfigManager.getTestDataValue("userIdForPostRequestPosts")));
        expectedPost.setTitle(GeneratingRandomString.generate((Integer.parseInt(ConfigManager.
                getTestDataValue("titleLengthForGetRequestOnePost")))));
        expectedPost.setBody(GeneratingRandomString.generate((Integer.parseInt(ConfigManager.
                getTestDataValue("bodyLengthForGetRequestOnePost")))));

        response = APIUtils.doPost(POSTS.getPoint(), JsonConverter.convertToString(expectedPost));

        PostModelForResponse actualPost = (PostModelForResponse) JsonConverter.
                convertToJson(response.getBody(), PostModelForResponse.class);

        Assert.assertEquals(response.getStatus(), HttpStatus.SC_CREATED, "Wrong status code returned");
        Assert.assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "Not correct post's userId");
        Assert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "Not correct post's title");
        Assert.assertEquals(actualPost.getBody(), expectedPost.getBody(), "Not correct post's body");
        Assert.assertNotEquals(actualPost.getId(), Integer.parseInt(ConfigManager.
                getTestDataValue("emptyIdForPostRequestPosts")), "Empty id");
    }
}
