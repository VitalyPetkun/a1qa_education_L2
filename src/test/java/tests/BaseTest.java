package tests;

import org.testng.annotations.BeforeMethod;
import utils.ConfigManager;
import utils.api.APIUtils;
import static services.Uri.*;

public class BaseTest {

    @BeforeMethod
    public void setupBaseUri() {
        APIUtils.setupBaseUri(ConfigManager.getConfigDataValue(MOCKY_URI.getUri()));
    }
}
