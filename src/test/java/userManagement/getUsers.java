package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class getUsers {

    @Test
    public void getUserData(){
       given().baseUri("https://jsonplaceholder.typicode.com").
       when().get("/posts/1").
       then().assertThat().statusCode(200);

    }

    @Test
    public void getData(){
    given().
    when().get("https://reqres.in/api/users?page=2").
    then().assertThat().statusCode(200);
    }

    @Test
    public void validateGetResponseBody(){
        // Set base URI for the API
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        // Send GET request and validate the response bod using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title",equalTo("delectus aut autem"))
                .body("userId",equalTo(1));

    }
// when we try to search a specific thing
    @Test
    public void validateResponseHasItems(){
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                .when()
                .get("/posts")
                .then()
                .extract().response();
        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
    }

    @Test
    public void validataResponseHasSize(){
        RestAssured.baseURI= "https://jsonplaceholder.typicode.com";
        Response response=given()
                .when().get("/comments")
                .then()
                .extract().response();
        assertThat(response.jsonPath().getList(""),hasSize(500));

    }

@Test
    public void validateListContainsItems(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract().response();
    List<String> expectedNames = Arrays.asList("Leanne Graham", "Ervin Howell", "Clementine Bauch");
    assertThat(response.jsonPath().getList("name"), hasItems(expectedNames.toArray(new String[0])));

   }

    @Test
    public void validateListContainsInOrder() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body contains specific items in a specific order
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
    }


}


