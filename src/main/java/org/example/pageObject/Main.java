package org.example.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    private final WebDriver webDriver;
    private final By goMain = By.xpath(".//p[text()='Конструктор']");//в хедере ссылка на конструтор для заказа
    private final By accountLink = By.xpath("//*[@id=\"root\"]/div/header/nav/a"); // линк на личный кабинет
    private final By buttonEntry = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button"); //кнопка войти в аккаунт
    private final By buttonBuns = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]"); // линк на булки
    private final By buttonSous = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]"); // линк на соусы
    private final By buttonInside = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]"); // линк на начинки
    private final By createBurgers = By.xpath("//*[@id=\"root\"]/div/main/section[1]/h1");
    private final By stellaBurger = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");
    private final By buns = By.xpath(".//h2[text()='Булки']");
    private final By sous = By.xpath(".//h2[text()='Соусы']");
    private final By inside = By.xpath(".//h2[text()='Начинки']");
    public Main(WebDriver webDriver) { //конструктор webDriver
        this.webDriver = webDriver;
    }

    public void goMainClick() {
        webDriver.findElement(goMain).click();
    }

    public void clikcButtonEntry() { //клик по кнопке Зарегестрироваться внизу страницы
        webDriver.findElement(buttonEntry).click(); //клик по кнопке "Войти в аккаунт"
        new WebDriverWait(webDriver, 5);
    }

    @Step("'Соберите бургер' в заголовке")
    public String visableCreateBurgers() {
        return webDriver.findElement(createBurgers).getText();
    }

    @Step("Булки есть в конструкторе")
    public String visableBuns() {
        return webDriver.findElement(buns).getText();
    }

    @Step("Соусы есть в конструкторе")
    public String visableSous() {
        return webDriver.findElement(sous).getText();
    }

    @Step("Начинки есть в конструкторе")
    public String visableInside() {
        return webDriver.findElement(inside).getText();
    }

    public void accountLinkclick() { //клик по кнопке Личный кабинет
        webDriver.findElement(accountLink).click();
        new WebDriverWait(webDriver, 5);
    }

    public void stellaBurgerClick() { //клик по кнопке Личный кабинет
        webDriver.findElement(stellaBurger).click();
        new WebDriverWait(webDriver, 5);
    }

    public void buttonBunsClick() { //клик по Булкам
        webDriver.findElement(buttonBuns).click();
        new WebDriverWait(webDriver, 5);
    }

    public void buttonSousClick() { //клик по Соусам
        webDriver.findElement(buttonSous).click();
        new WebDriverWait(webDriver, 5);
    }

    public void buttonInsideClick() { //клик по начинкам
        webDriver.findElement(buttonInside).click();
        new WebDriverWait(webDriver, 5);
    }

    public void waitForLoadPage() {//ожидание
        new WebDriverWait(webDriver, 5);
    }
}