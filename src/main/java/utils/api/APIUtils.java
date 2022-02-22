package utils.api;

import utils.SmartLogger;

import static io.restassured.RestAssured.*;

public class APIUtils {

    private APIUtils() {
    }

    public static void setupBaseUri(String currentBaseUri) {
        SmartLogger.logInfo("Setup baseUri");
        baseURI = currentBaseUri;
    }

    public static Response doGet(String endPoint) {
        SmartLogger.logInfo("Get request " + baseURI + endPoint);
        return new Response(given().get(endPoint).then());
    }
}
