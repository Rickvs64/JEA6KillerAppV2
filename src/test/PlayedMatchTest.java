import Domains.Gamemode;
import Domains.PlayedMatch;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class PlayedMatchTest extends FunctionalTest{

    @Test
    public void basicPingTest() {
        given()
                .log().all()
                .when()
                .get("/match")
                .then()
                .statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given()
                .log().all()
                .when()
                .get("/match/test").then()
                .statusCode(404);
    }

    @Test
    public void getMatchById() {
        given()
                .log().all()
                .when()
                .get("/match/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1));
    }

    @Test
    public void getAllMatches() {
        given()
                .log().all()
                .when()
                .get("/match")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    public void addEmptyMatch() {
        PlayedMatch match = new PlayedMatch();

        given()
                .log().all()
                .contentType("application/json")
                .body(match)
                .when().post("/match").then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("New match has been added."));
    }
}
