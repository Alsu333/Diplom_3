package YandexDriverTest;

import io.qameta.allure.junit4.DisplayName;
import org.example.User;
import org.example.UserClient;
import org.example.pageObject.Login;
import org.example.pageObject.Main;
import org.example.pageObject.Profile;
import org.example.pageObject.Register;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class YandexLoginTest {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private Login login;
    private Main main;
    private Register register;
    private Profile profile;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\alsuy\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        user = new User("369801@yandex.ru", "3698passwrd", "Kate");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userClient = new UserClient();
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void LoginWithCorrectData() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        main = new Main(driver);
        main.waitForLoadPage();
        main.accountLinkclick();
        login = new Login(driver);
        login.waitForLoadPage();
        login.emailFieldSend(user.getEmail());
        login.passwordFieldSend(user.getPassword());
        login.clickLoginButton();
        login.waitForLoadPage();
        String expected = "Соберите бургер";
        String actual = main.visableCreateBurgers();
        Assert.assertEquals("Fail, try again", expected, actual);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void LoginWithForgottesPWD() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        main = new Main(driver);
        main.clikcButtonEntry();
        register = new Register(driver);
        register.forgotPWDButton();
        register.forgotPassportEntryButton();
        login = new Login(driver);
        login.waitForLoadPage();
        login.emailFieldSend(user.getEmail());
        login.passwordFieldSend(user.getPassword());
        login.clickLoginButton();
        login.waitForLoadPage();
        main.accountLinkclick(); //Проверили переход по клику на «Личный кабинет».
        profile = new Profile(driver);
        profile.clickExitButton();
        profile.waitForLoadPage();
        String expected = "Вход";
        String actual = register.getEntryText();
        Assert.assertEquals("Fail, try again", expected, actual); // проверяем работу кнопки Выйти
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации") //вход по кнопке «Войти в аккаунт» на главной,
    public void entryWithButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        Register register = new Register(driver);
        main = new Main(driver);
        main.waitForLoadPage();
        main.clikcButtonEntry(); //кликаем "Войти в аккаунт"
        register.waitForLoadPage();
        register.clikcForRegisterButton();//кликаем Зарегистрироваться внизу страницы
        register.clikcEntryButtoninRegister(); // кликаем на Войти внизу страницы
        String actualNameEntry = register.getEntryText();
        String expectedNameEntry = "Вход";
        Assert.assertEquals("Fail, try again", expectedNameEntry, actualNameEntry);
        register.waitForLoadPage();
        login = new Login(driver);
        login.emailFieldSend(user.getEmail());
        login.passwordFieldSend(user.getPassword());
        login.clickLoginButton();
        login.waitForLoadPage();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void LoginWithEntryButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        main = new Main(driver);
        main.clikcButtonEntry();
        register = new Register(driver);
        String actualName = register.getEntryText();
        String expectedName = "Вход";
        Assert.assertEquals("Fail, try again", expectedName, actualName);
        register.waitForLoadPage();
        login = new Login(driver);
        login.emailFieldSend(user.getEmail());
        login.passwordFieldSend(user.getPassword());
        login.clickLoginButton();
        login.waitForLoadPage();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
