package utils.API;

import io.restassured.response.ValidatableResponse;

public class Response {

    private static int status;
    private static String body;

    public Response(ValidatableResponse validatableResponse) {
        status = validatableResponse.extract().statusCode();
        body = validatableResponse.extract().body().asString();
    }

    public static int getStatus() {
        return status;
    }

    public static String getBody() {
        return body;
    }
}
