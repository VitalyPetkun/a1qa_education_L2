package utils.api;

import io.restassured.http.ContentType;
import utils.SmartLogger;

import static io.restassured.RestAssured.*;

public class VkApiUtils {

    private VkApiUtils() {
    }

    public static void setupBaseUri(String currentBaseUri) {
        SmartLogger.logInfo("Setup baseUri");
        baseURI = currentBaseUri;
    }

    public static Response doGet(String endPoint) {
        SmartLogger.logInfo(String.format("Get request %s%s", baseURI, endPoint));
        return new Response(given().get(endPoint).then());
    }

    public static Response doPost(String endPoint, String body) {
        SmartLogger.logInfo(String.format("Post request %s%s", baseURI, endPoint));
        return new Response(
                given().
                        header("Content-Type", "application/json").
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        body(body).
                        when().
                        post(endPoint).then()
        );
    }
}
