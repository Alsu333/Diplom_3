package YandexDriverTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.example.User;
import org.example.UserClient;
import org.example.pageObject.Login;
import org.example.pageObject.Main;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class YandexConstructorTest {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private Login login;
    private Main main;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\alsuy\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userClient = new UserClient();
        user = new User("369801@yandex.ru", "3698passwrd", "Kate");
    }

    @Test
    @DisplayName("переход по клику на «Конструктор» и на логотип Stellar Burgers")
    public void linkAccountTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        main = new Main(driver);
        main.waitForLoadPage();
        main.clikcButtonEntry();
        login = new Login(driver);
        login.waitForLoadPage();
        login.emailFieldSend(user.getEmail());
        login.passwordFieldSend(user.getPassword());
        login.clickLoginButton();
        login.waitForLoadPage();
        main = new Main(driver);
        main.accountLinkclick(); // переход на личный каюинет
        main.waitForLoadPage();
        main.stellaBurgerClick();
        main.waitForLoadPage();
        String actual = main.visableCreateBurgers();
        String expected = "Соберите бургер";
        Assert.assertEquals("Fail, something wrong", expected, actual);
    }

    @Test
    @DisplayName("работают переходы к разделам:«Соусы»")
    public void CheckConstructorTest() {
        linkAccountTest();
        main = new Main(driver);
        main.goMainClick();
        main.buttonSousClick();
        String actual = main.visableSous();
        String expected = "Соусы";
        Assert.assertEquals("Fail, something wrong", expected, actual);
    }

    @Test
    @DisplayName("работают переходы к разделам:«Начинки»")
    public void CheckInsideTest() {
        CheckConstructorTest();
        main = new Main(driver);
        main.buttonInsideClick();
        String actualInside = main.visableInside();
        String expectedInside = "Начинки";
        Assert.assertEquals("Fail, something wrong", expectedInside, actualInside);
    }

    @Test
    @DisplayName("работают переходы к разделам:«Булки»")
    public void CheckBunsTest() {
        CheckConstructorTest();
        main = new Main(driver);
        main.buttonBunsClick();
        String actualBuns = main.visableBuns();
        String expectedBuns = "Булки";
        Assert.assertEquals("Fail, something wrong", expectedBuns, actualBuns);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
