package org.example;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    public static final String URL = "https://stellarburgers.nomoreparties.site/api";
    public static final String ApiLogin = "/auth/login";
    public static final String ApiDelete = "/auth/user";

    @Step("Получить accessToken пользователя")
    public String getAccessTokenOnLogin(User user) {
        return given()
                .spec(spec())
                .body(user)
                .when()
                .post(URL + ApiLogin)
                .then().log().all()
                .extract()
                .path("accessToken");
    }

    @Step("Удаление пользовательских данных из системы")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(spec())
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .when()
                .delete(URL + ApiDelete)
                .then().log().all();
    }
}
