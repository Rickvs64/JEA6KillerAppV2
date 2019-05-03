import Domains.Account;
import Domains.EPlatform;
import Domains.Hero;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class HeroTest extends FunctionalTest{

    @Test
    public void basicPingTest() {
        given()
                .log().all()
                .when()
                .get("/hero")
                .then()
                .statusCode(200);
    }

    @Test
    public void incorrectUrl() {
        given()
                .log().all()
                .when()
                .get("/hero/test").then()
                .statusCode(404);
    }

    @Test
    public void getHeroById() {
        given()
                .log().all()
                .when()
                .get("/hero/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1));
    }

    @Test
    public void getAllHeroes() {
        given()
                .log().all()
                .when()
                .get("/hero")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    public void addHero() {
        String unique = "Hero_RestAssured_" + UUID.randomUUID().toString().substring(0, 9);
        Hero hero = new Hero(unique, "Created by RestAssured.");

        given()
                .log().all()
                .contentType("application/json")
                .body(hero)
                .when().post("/hero").then()
                .statusCode(200)
                .assertThat()
                .body(equalTo(hero.getName() + " has been added."));
    }
}
