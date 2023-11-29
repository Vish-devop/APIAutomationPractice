package userManagement;


//import com.google.gson.stream.JsonReader;
//import utils.JsonReader;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import utils.*;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;



public class getUsers {
    @Test(groups = "SmokeSuite") //testng annotation: used for specifying methods as test-cases.
    public void validateGetResponseBody(){    //this validateGetResponseBody is a test-case.

// Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("userId", equalTo(1));
    }
    //using Hamcrest librabry:
    @Test
    public void validateGetResponseBodyUsingHamcrest(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

//        sending the GET request and storing the response in a variable.
        Response response=given()
                .when()
                .get("/todos/1")
                .then()
                .extract()
                .response();

//        validating the GET that is it empty or not?
        assertThat(response.getBody().asString(), not(isEmptyString()));
//        validate the GET that iit's contains a specific value
        assertThat(response.getBody().asString(), containsString(("delectus aut autem")));
//        validating the GET that it has a specific JSON value
        assertThat(response.getBody().jsonPath().get("userId"), equalTo(1));

    }
//validating headers in Rest Assured
    @Test(groups = {"SmokeSuite","RegressionSuite"})
    public void testGetUserListWithHeader(){
        RestAssured.baseURI="https://reqres.in/api";

        Response response=given()
                .header("Content-Type","application/json")
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                .when()
                .get("/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Object token = response.jsonPath().get("token");
        System.out.println("Test Case 1 passed");
    }
@Test(groups = "RegressionSuite")
    public void testWithTwoHeaders(){
    given()
            .header("Authorization","bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
            .header("Content-Type","application/json")
            .when()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200);
    System.out.println("Test case 2 passed");
}
    @Test(groups = "RegressionSuite")
    public void testGetUserList(){
        RestAssured.baseURI="https://reqres.in/api";

//    creating a Map to hold multiple headers
        Map<String,String> headers=new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer <your_token_here>");

//        sending a GET request with headers
        given()
                .headers(headers)
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"));
    }

    @Test
//    validating DELETE Method with Rest Assured
    public void verifyStatusCodeDelete(){
        Response resp=given().delete("https://reqres.in/api/users/2");
        assertEquals(resp.getStatusCode(), StatusCode.NO_CONTENT.code);
    }

    @Test  // validating the Query Parameters
    public void testGetUsersWithQueryParameters(){
        Response response = given()
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();


//        Asserting that the response contains 6 users
        response.then().body("data",hasSize(6));

//        Asserting that the first user in the list has the correct values
        response.then().body("data[0].id",is(7));
        response.then().body("data[0].email",is("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name",is("Michael"));
        response.then().body("data[0].last_name",is("Lawson"));
        response.then().body("data[0].avatar",is("https://reqres.in/img/faces/7-image.jpg"));

        System.out.println("Single Query sucedded");
    }

    @Test
    //validating multiple param queries
    public void validateMultipleQueriesParam(){
        Response response=given()
                .queryParam("page",2)
                .queryParam("per_page",3)
                .queryParam("rtqsdr",4)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(StatusCode.SUCCESS.code)
                .extract()
                .response();

    }


    @Test  //validating form parameters
    public void validateFormParam(){
        RestAssured.baseURI="https://reqres.in/api";

        Response response=given()
                .contentType(ContentType.URLENC)
                .formParam("name","John Doe")
                .formParam("job","Developer")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

//        asserting that the response contains the correct name and job values
        response.then().body("name",equalTo("John Doe"));
        response.then().body("job",equalTo("Developer"));

    }

    @Test //validating cookies
    public void validateCookeis(){

        //setting up the base URL and path of endpoint
        String baseURL="https://httpbin.org";
        String path="/cookies";

//        setting up the cookies for sending with the request
        Map<String, String> cookies=new HashMap<>();
        cookies.put("cookie1","value1");
        cookies.put("cookie2","value2");

//        send the request with the cookies
        given()
                .cookies(cookies)
                .when()
                .get(baseURL+path)
                .then()
                .statusCode(200)
                .body("cookies.cookie1",equalTo("value1"))
                .body("cookies.cookie2",equalTo("value2"));
    }



    @Test(description = "soft Assertions")
    public void  softAssertion(){
        System.out.println("softAssert");

    }

    @Test(description = "validating of soft assertions")
    public void validatingSoftAssertions(){
        Response response=given()
                .pathParam("raceSeason",2017)
                .when()
                .get("http://ergast.com/api/f1/{raceSeason}/circuits.json");

        int actualStatusCode=response.statusCode();
        assertEquals(actualStatusCode,200);


        response.then().body("data",hasSize(6));
    }


}







