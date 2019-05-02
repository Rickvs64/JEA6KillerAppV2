import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountTest extends FunctionalTest{

    @Test
    public void basicPingTest() {
        given().log().all().when().get("/account").then().statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given().log().all().when().get("/account/test").then()
                .statusCode(404);
    }

    @Test
    public void getAccountById() {
        given().log().all().when().get("/account/1").then()
                .statusCode(200)
                .assertThat()
                .body("result.id", equalTo(1));
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
