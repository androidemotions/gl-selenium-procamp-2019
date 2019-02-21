import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstTestClass {

    WebDriver drv;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-fullscreen");

        drv = new ChromeDriver(options);


    }

    @After
    public void stop() {
        drv.quit();
    }


    @Test
    public void firstTest() throws InterruptedException {
        drv.get("http://www.google.com");
    }

}
