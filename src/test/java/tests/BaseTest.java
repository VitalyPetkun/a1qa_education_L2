package tests;

import org.testng.annotations.BeforeMethod;
import utils.APIUtils;
import utils.ConfigManager;
import utils.MyLogger;

public class BaseTest {

    private static final String BASE_URI = ConfigManager.getTestDataString("baseURI");

    @BeforeMethod
    public void setupBaseUri() {
        MyLogger.logInfo("Start tests");
        APIUtils.setupBaseUri(BASE_URI);
    }
}
