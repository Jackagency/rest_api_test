package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    void singleUser() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void singleResource() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.pantone_value", is("17-2031"));
    }

    @Test
    void userCreate() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .body(requestBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void userUpdate() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .body(requestBody)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void userDelete() {
        given()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
}
