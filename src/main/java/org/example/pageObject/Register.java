package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register {
    private final By errorIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By entryText = By.xpath(".//h2[text() = 'Вход']");
    private final WebDriver webDriver;
    private final By nameField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"); // поле имени
    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input"); // поле почты
    private final By passwordField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input"); // поле пароля
    private final By registrButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button"); // кнопка регистрации
    private final By forRegister = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a"); // кнопка для входа в систему
    private final By ForgotPassword = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a"); //линк Восстановить пароль
    private final By forgotPassportEntry = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a"); //линк на вход в сиситему во вкладку orgot password
    private final By EntryButtoninRegister = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    public Register(WebDriver webDriver) { //конструктор webDriver
        this.webDriver = webDriver;
    }

    public void waitForLoadPage() {//ожидание
        new WebDriverWait(webDriver, 5);
    }

    public void clikcRegistrButton() { //клик по кнопке Зарегистрироваться внизу страницы
        webDriver.findElement(registrButton).click();
        new WebDriverWait(webDriver, 5);
    }

    public void clikcForRegisterButton() { //клик по кнопке
        webDriver.findElement(forRegister).click();
        new WebDriverWait(webDriver, 5);
    }

    public void NameGet(String Name) {
        webDriver.findElement(nameField).sendKeys(Name); // вводим значение имени
    }

    public void EmailGet(String Surname) {
        webDriver.findElement(emailField).sendKeys(Surname); // вводим значение почты
    }

    public void PasswordGet(String Address) {
        webDriver.findElement(passwordField).sendKeys(Address); // вводим значение пароля
    }

    public void forgotPWDButton() { //клик по кнопке Восстановить пароль
        webDriver.findElement(ForgotPassword).click();
        new WebDriverWait(webDriver, 5);
    }

    public void forgotPassportEntryButton() { //клик по кнопке Восстановить пароль
        webDriver.findElement(forgotPassportEntry).click();
        new WebDriverWait(webDriver, 5);
    }

    @Step("некорректный пароль")
    public String getErrorPasswordText() {
        return webDriver.findElement(errorIncorrectPassword).getText();
    }

    @Step("Вход в заголовке")
    public String getEntryText() {
        return webDriver.findElement(entryText).getText();
    }

    public void clikcEntryButtoninRegister() { //клик по кнопке
        webDriver.findElement(EntryButtoninRegister).click();
        new WebDriverWait(webDriver, 5);
    }
}
