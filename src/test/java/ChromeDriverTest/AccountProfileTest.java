package ChromeDriverTest;

import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.util.concurrent.TimeUnit;

public class AccountProfileTest {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private Login login;
    private Main main;
    private Register register;
    private Profile profile;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        user = new User("369801@yandex.ru", "3698passwrd", "Kate");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userClient = new UserClient();
    }

    @Test
    @DisplayName(" переход по клику на «Личный кабинет»")
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
        main.accountLinkclick();
        profile = new Profile(driver);
        String expected = "Профиль";
        String actual = profile.getTitleTextProfile();
        Assert.assertEquals("Fail, try again", expected, actual);
    }

    @Test
    @DisplayName("выход по кнопке «Выйти» в личном кабинете")
    public void ExitTest() {
        linkAccountTest();
        profile = new Profile(driver);
        profile.clickExitButton();
        String actualName = profile.getTestExit();
        String expectedName = "Выход";
        Assert.assertEquals("Fail, try again", expectedName, actualName);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}