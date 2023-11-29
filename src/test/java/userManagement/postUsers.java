package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class postUsers {

    @Test
    public void validatePostAsString(){
        RestAssured.baseURI="https://reqres.in/api";

        Response response=given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");


        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
//        Object token = response.jsonPath().get("token");
        System.out.println(response.body().asString());
    }

    @Test
    public void validatePostAsJSONFile() throws IOException {


        Response response=given()
                .header("Content-Type","application/json")
                .body(IOUtils.toString(
                        new FileInputStream(new File(
                                System.getProperty("user.dir")+ "/resources/TestData/postRequestBody.json"))

                ))
                .when()
                .post("/users");

                assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
                System.out.println(response.body().asString());
    }

    @Test
    public void validatePostAsPOJO(){
        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");

        Response response=given()
                .header("Content-Type","application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");

        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojo is successfully done");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPOJOList(){
        List<String> listlanguage = new ArrayList<>();
        listlanguage.add("Python");
        listlanguage.add("Java");

        postRequestBody postRequest=new postRequestBody();
        postRequest.setName("morpheus");
        postRequest.setLanguages(listlanguage);
        postRequest.setJob("leader");

        Response response = given()
                .header("Content-Type","application/json")
        .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");

        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validating with pojo as List is successfullly executed");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePOSTWithPojo(){
       cityRequest cityRequest1 = new cityRequest();
        cityRequest1.setName("Bangalore");
        cityRequest1.setTemperature("30");

        cityRequest cityRequest2 = new cityRequest();
        cityRequest2.setName("Delhi");
        cityRequest2.setTemperature("40");

        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequest1);
        cityRequests.add(cityRequest2);

        List<String> listlanguage = new ArrayList<>();
        listlanguage.add("Python");
        listlanguage.add("Java");

        postRequestBody postRequest=new postRequestBody();
        postRequest.setName("morpheus");
        postRequest.setLanguages(listlanguage);
        postRequest.setJob("leader");

        Response response=given()
                .header("Content-Type", "application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");

        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }

//    doing the deserilization

    @Test
    public void validatePOSTWithRequestPojo(){
        cityRequest cityRequest1 = new cityRequest();
        cityRequest1.setName("Bangalore");
        cityRequest1.setTemperature("30");

        cityRequest cityRequest2 = new cityRequest();
        cityRequest2.setName("Delhi");
        cityRequest2.setTemperature("40");

        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequest1);
        cityRequests.add(cityRequest2);

        List<String> listlanguage = new ArrayList<>();
        listlanguage.add("Python");
        listlanguage.add("Java");

        postRequestBody postRequest=new postRequestBody();
        postRequest.setName("morpheus");
        postRequest.setLanguages(listlanguage);
        postRequest.setJob("leader");
        postRequest.setCityRequestBody();

        Response response=given()
                .header("Content-Type", "application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getcityRequestBody().get(0));
        System.out.println(responseBody.getLanguages());
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }
}
