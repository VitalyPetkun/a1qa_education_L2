package utils.API;

import io.restassured.http.ContentType;
import utils.Logger;
import static io.restassured.RestAssured.*;

public class APIUtils {

    public static void setupBaseUri(String BASE_URI) {
        Logger.logInfo("Setup baseUri");
        baseURI = BASE_URI;
    }

    public static Response doGet(String endPoint) {
        Logger.logInfo("Get request " + baseURI + endPoint);
        return new Response(given().get(endPoint).then());
    }

    public static Response doPost(String endPoint, String body) {
        Logger.logInfo("Post request " + baseURI + endPoint);
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
