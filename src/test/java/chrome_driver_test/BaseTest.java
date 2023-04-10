package chrome_driver_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        /*Yandex browser:
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriverya106.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\svetl\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
         */

        //Chrome browser:
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
