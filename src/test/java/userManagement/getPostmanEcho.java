package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;
import java.text.ParseException;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class getPostmanEcho {

    @Test //validating Basic Auth
    public void validateResponseBodyGetBasicAuth(){
        Response response=given()
                .auth()
                .basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth");

        int actualStatusCode=response.statusCode();
        assertEquals(actualStatusCode,200);
        System.out.println(response.body().asString());
    }

    @Test(description = "Validation of Digest Auth", groups = "SmokeSuits")
    public void validateResponseBodyGetDigestAuth(){
        Response response=given()
                .auth()
                .digest("postman","password")
                .when()
                .get("https://postman-echo.com/digest-auth");

        assertEquals(response.statusCode(),200);
        System.out.println(response.body().asString());
    }

    @Test(description = "Validating Test Data with JSON")
    public void validateTestDataWithJson() throws IOException, ParseException, org.json.simple.parser.ParseException {
        String username,password;
        username= JsonReader.getTestData("username");
        password=JsonReader.getTestData("password");
        System.out.println("username for json is: "+username + "**passoword from json is:" + password);

        Response response =given()
                .auth()
                .basic(username, password)
                .when()
                .get("https://postman-echo.com/basic-auth");

        assertEquals(response.statusCode(),200);
        System.out.println("validate with test data from json executed successfully");
    }

}
