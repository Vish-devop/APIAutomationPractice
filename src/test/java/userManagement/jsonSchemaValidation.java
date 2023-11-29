package userManagement;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class jsonSchemaValidation {

    @Test
    public void jsonSchemaValidation(){
        File schema= new File("resources/ExpectedSchema.json");
        given()
                .when()
                .get("https://reqres.in/api/users?pages=2")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(schema))
                .assertThat()
                .statusCode(200);
    }

}
