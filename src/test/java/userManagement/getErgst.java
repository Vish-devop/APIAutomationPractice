package userManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class getErgst {
    @Test //Automating Path Parameters
    public void validingPathParamaters(){
        Response response=given()
                .pathParams("raceSeason",2017)
                .when()
                .get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
                .then()
                .statusCode(StatusCode.SUCCESS.code)
                .extract()
                .response();

        System.out.println("Path Parameter Sucedded");

    }

    @Test   //printing the resource body into console
    public void validateGetResponseBodyGetPathParam(){
        Response response=given()
                .pathParam("raceSeason",2017)
                .when()
                .get("http://ergast.com/api/f1/{raceSeason}/circuits.json");

        int actualStatusCode=response.statusCode();
        assertEquals(actualStatusCode,200);

        // this line will help in printing the resource into console
        System.out.println(response.body().asString());
    }
}
