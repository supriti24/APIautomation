package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestHeaders {

    private String token;

    @BeforeClass
    public void setup() {
        // Set base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                .when()
                .get("/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        token = response.jsonPath().get("token");
    }

    @Test
    public void testGetUserListWithHeader() {
        given()
                .header("Content-Type","application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("testGetUserListWithHeader Executed Successfully");
    }


    @Test
    public void testWithTwoHeaders() {
        given()
                .header("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
                .header("Content-Type", "application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("testWithTwoHeaders Executed Successfully");
    }

}
