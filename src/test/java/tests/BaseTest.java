package tests;

import org.testng.annotations.BeforeMethod;
import utils.api.APIUtils;
import static services.Uri.JSON_PLACEHOLDER_URI;

public class BaseTest {

    @BeforeMethod
    public void setupBaseUri() {
        APIUtils.setupBaseUri(JSON_PLACEHOLDER_URI.getUri());
    }
}
