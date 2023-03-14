package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    private final WebDriver webDriver;
    private final By enterSection = By.xpath(".//h2[text()='Вход']"); //Секция логин
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input"); //поле почты
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");//поле пароля
    private final By logInButton = By.xpath(".//button[text()='Войти']"); //кнопка Войти
    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitForLoadPage() {//ожидание
        new WebDriverWait(webDriver, 5);
    }

    @Step("Вход в звголовке")
    public String getTitleTextInput() {
        return webDriver.findElement(enterSection).getText();
    }

    public void emailFieldSend(String text) {
        webDriver.findElement(emailField).sendKeys(text);
    }

    public void passwordFieldSend(String text) {
        webDriver.findElement(passwordField).sendKeys(text);
    }

    public void clickLoginButton() {
        webDriver.findElement(logInButton).click();
    }
}
