import Domains.Gamemode;
import Domains.Hero;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class GamemodeTest extends FunctionalTest{

    @Test
    public void basicPingTest() {
        given()
                .log().all()
                .when()
                .get("/gamemode")
                .then()
                .statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given()
                .log().all()
                .when()
                .get("/gamemode/test").then()
                .statusCode(404);
    }

    @Test
    public void getGamemodeById() {
        given()
                .log().all()
                .when()
                .get("/gamemode/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1));
    }

    @Test
    public void getAllGamemodes() {
        given()
                .log().all()
                .when()
                .get("/gamemode")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    public void addGamemode() {
        String unique = "RestAssured_" + UUID.randomUUID().toString().substring(0, 9);
        Gamemode gamemode = new Gamemode(unique, "Created by RestAssured.", 10.0f);

        given()
                .log().all()
                .contentType("application/json")
                .body(gamemode)
                .when().post("/gamemode").then()
                .statusCode(200)
                .assertThat()
                .body(equalTo(gamemode.getName() + " has been added."));
    }
}
