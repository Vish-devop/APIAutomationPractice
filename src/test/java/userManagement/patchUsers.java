package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class patchUsers {

    @Test
    public void validatingPatchAsString(){
        RestAssured.baseURI="https://reqres.in/api";

        Response response=given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"morpheus\"")
                        .when()
                        .patch("https://reqres.in/api/users/2");

        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());
    }
}
