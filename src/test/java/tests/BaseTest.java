package tests;

import org.testng.annotations.BeforeMethod;
import utils.api.APIUtils;
import static services.Uri.*;

public class BaseTest {

    @BeforeMethod
    public void setupBaseUri() {
        APIUtils.setupBaseUri(MOCKY_URI.getUri());
    }
}
