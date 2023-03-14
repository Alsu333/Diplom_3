package YandexDriverTest;

import io.qameta.allure.junit4.DisplayName;
import org.example.User;
import org.example.UserClient;
import org.example.UserGenerator;
import org.example.pageObject.Login;
import org.example.pageObject.Main;
import org.example.pageObject.Register;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class YandexRegistrationtTest {
    private WebDriver driver;
    private User user;
    private String accessToken;
    private UserClient userClient;
    private Login login;
    private Main main;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\alsuy\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        user = UserGenerator.getDefault();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Успешная регистрация нового пользоватлея") //вход по кнопке «Войти в аккаунт» на главной,
    public void NewAccountPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        Register register = new Register(driver);
        main = new Main(driver);
        main.waitForLoadPage();
        main.clikcButtonEntry();
        register.waitForLoadPage();
        register.clikcForRegisterButton();
        register.NameGet(user.getName());
        register.EmailGet(user.getEmail());
        register.PasswordGet(user.getPassword());
        register.clikcRegistrButton();
        login = new Login(driver);
        login.waitForLoadPage();
        login.emailFieldSend(user.getEmail());
        login.passwordFieldSend(user.getPassword());
        String expected = "Вход";
        String actual = login.getTitleTextInput();
        Assert.assertEquals("Fail", expected, actual);
        login.clickLoginButton();
        login.waitForLoadPage();
        accessToken = userClient.getAccessTokenOnLogin(user);
        userClient.delete(accessToken);
    }

    @Test
    @DisplayName("регистрация пользоватлея с невалидным паролем")
    public void AccountWithWrongPWD() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        Register register = new Register(driver);
        register.waitForLoadPage();
        main = new Main(driver);
        main.clikcButtonEntry();
        register.clikcForRegisterButton();
        register.NameGet(user.getName());
        register.EmailGet(user.getEmail());
        register.PasswordGet("147*");
        register.clikcRegistrButton();
        String actualError = register.getErrorPasswordText();
        String expectedError = "Некорректный пароль";
        Assert.assertEquals("Минимальный пароль — шесть символов", expectedError, actualError);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
