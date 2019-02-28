import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirstTestClass {

    WebDriver drv;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
      //  WebDriverManager.edgedriver().setup();
       // WebDriverManager.firefoxdriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("w3c",true);
        //options.addArguments("start-fullscreen");

        drv = new ChromeDriver(options);
        //drv = new FirefoxDriver();
        //drv = new EdgeDriver();


    }

    @After
    public void stop() {
        drv.quit();
    }


    @Test
    public void firstTest() throws InterruptedException {
        drv.get("http://www.google.com");

        WebElement search = drv.findElement(By.name("q"));

        search.sendKeys("asasasas");

        search = drv.findElement(By.name("q"));
        search.sendKeys("asasasas");

        search = drv.findElement(By.name("q"));
        search.sendKeys("asasasas");

    }

}
