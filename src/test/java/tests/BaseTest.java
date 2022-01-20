package tests;

import org.testng.annotations.BeforeMethod;
import utils.APIUtils;
import utils.ConfigManager;

public class BaseTest {

    private static final String BASE_URI = ConfigManager.getTestDataString("baseURI");

    @BeforeMethod
    public void setupBaseUri() {
        APIUtils.setupBaseUri(BASE_URI);
    }
}
