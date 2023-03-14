package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Profile {
    private final WebDriver webDriver;
    private final By exitButton = By.xpath(".//li[3]/button[text() = 'Выход']");
    private final By profile = By.xpath(".//li[1]/a[text()='Профиль']");
    public Profile(WebDriver webDriver) { //конструктор webDriver
        this.webDriver = webDriver;
    }

    public void clickExitButton() { //клик по кнопке Выход
        webDriver.findElement(exitButton).click();
        new WebDriverWait(webDriver, 5);
    }

    @Step("Строка 'Профиль' в личном кабинете")
    public String getTitleTextProfile() {
        return webDriver.findElement(profile).getText();
    }

    @Step("Строка 'Выход' в личном кабинете")
    public String getTestExit() {
        return webDriver.findElement(exitButton).getText();
    }

    public void waitForLoadPage() {//ожидание
        new WebDriverWait(webDriver, 5);
    }
}
