package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static listeners.CustomAllureListener.withCustomTemplates;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    @DisplayName("Получение одного пользователя")
    void singleUser() {
        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Получение одного товара")
    void singleResource() {
        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.pantone_value", is("17-2031"));
    }

    @Test
    @DisplayName("Создание пользователя")
    void userCreate() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .filter(withCustomTemplates())
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
    @DisplayName("Редактирование пользователя")
    void userUpdate() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .filter(withCustomTemplates())
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
    @DisplayName("Удаление пользователя")
    void userDelete() {
        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
}
