package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.postRequestBody;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static junit.framework.Assert.assertEquals;

public class putUsers {

    @Test
    public void validatePUTAsString(){
        RestAssured.baseURI="https://reqres.in/api";

        Response response=given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}")
                .when()
                .put("/users/2");

        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());

    }

    @Test
    public void validatePutWithPojo(){
        postRequestBody postRequest= new postRequestBody();
        postRequest.setJob("zion resident");
        postRequest.setName("morpheus");

        Response response=given()
                .header("Content-Type","application/json")
                .when()
                .body(postRequest)
                .post("https://reqres.in/api/users");

        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());


    }
}


