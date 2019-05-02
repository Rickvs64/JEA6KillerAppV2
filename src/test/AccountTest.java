import Domains.Account;
import Domains.EPlatform;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountTest extends FunctionalTest{

    @Test
    public void basicPingTest() {
        given().log().all().when().get("/account").then().statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given()
                .log().all()
                .when()
                .get("/account/test").then()
                .statusCode(404);
    }

    @Test
    public void getAccountById() {
        given()
                .log().all()
                .when()
                .get("/account/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("result.id", equalTo(1));
    }

    @Test
    public void getAllAccounts() {
        given()
                .log().all()
                .when()
                .get("/account")
                .then()
                .statusCode(200)
                .assertThat()
                .body("result", hasSize(greaterThan(0)));
    }

    @Test
    public void addAccount() {
        String unique = "RestAssured_" + UUID.randomUUID().toString().substring(0, 9);
        Account account = new Account(unique, EPlatform.PC);

        given()
                .log().all()
                .contentType("application/json")
                .body(account)
                .when().post("/account").then()
                .statusCode(200)
                .assertThat()
                .body("result", equalTo(account.getName() + " has been added."));
    }
}
