import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountTest extends FunctionalTest {
    @Test
    public void basicPingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given().when().get("/rest/UNDEFINED").then()
                .statusCode(404);
    }

    @Test
    public void getAccountById() {
        // Not yet implemented!

        given().when().get("/rest/account/1").then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("Admin"));
    }

    @Test
    public void getAllAccounts() {
        // Not yet implemented!

        //      given().when().get("/rest/account").then()
        //      .statusCode(200)
        //      .assertThat()
        //      ...?
    }

    @Test
    public void addAccount() {
        // Not yet implemented!
    }
}
