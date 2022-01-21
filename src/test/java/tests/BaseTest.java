package tests;

import org.testng.annotations.BeforeMethod;
import utils.API.APIUtils;

import static services.URI.BASE_URI;

public class BaseTest {

    @BeforeMethod
    public void setupBaseUri() {
        APIUtils.setupBaseUri(BASE_URI.getUri());
    }
}
