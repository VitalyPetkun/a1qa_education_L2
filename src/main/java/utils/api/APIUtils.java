package utils.api;

import io.restassured.http.ContentType;
import utils.SmartLogger;

import static io.restassured.RestAssured.*;

public class APIUtils {

    private APIUtils() {
    }

    public static void setupBaseUri(String currentBaseUri) {
        SmartLogger.logInfo("Setup baseUri");
        baseURI = currentBaseUri;
    }

    public static Response doGet(String endPoint, ContentType contentType) {
        SmartLogger.logInfo("Get request " + baseURI + endPoint);
        return new Response(given()
                    .accept(contentType)
                .when()
                    .get(endPoint)
                .then());
    }
}
