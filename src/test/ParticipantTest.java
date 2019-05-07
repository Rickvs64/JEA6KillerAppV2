import Domains.Hero;
import Domains.Participant;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class ParticipantTest extends FunctionalTest{

    @Test
    public void basicPingTest() {
        given()
                .log().all()
                .when()
                .get("/participant")
                .then()
                .statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given()
                .log().all()
                .when()
                .get("/participant/test").then()
                .statusCode(404);
    }

    @Test
    public void getParticipantById() {
        given()
                .log().all()
                .when()
                .get("/participant/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1));
    }

    @Test
    public void getAllParticipants() {
        given()
                .log().all()
                .when()
                .get("/participant")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    public void addParticipant() {
        Participant participant = new Participant(1000, 1, 1, 15, 5, 20, 1);

        given()
                .log().all()
                .contentType("application/json")
                .body(participant)
                .when().post("/participant").then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("Participant has been added."));
    }

    @Test
    public void getAllParticipantsByAccountId() {
        given()
                .log().all()
                .when()
                .get("/participant/byaccount/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0));
    }
}
