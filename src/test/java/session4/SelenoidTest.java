package session4;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidTest {

    static RemoteWebDriver drv;

    @BeforeClass
    public static void start() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("72.0");

        drv = new RemoteWebDriver(
                URI.create("http://192.168.56.102:4455/wd/hub").toURL(),
                capabilities
        );

        drv.manage().window().setSize(new Dimension(1800, 800));

    }

    @Test
    public void remoteTest() {
        drv.get("http://google.com");
        drv.findElementByName("q").sendKeys("Remote WebDriver" + Keys.ENTER);
    }

    @AfterClass
    public static void stop() {
        drv.quit();
    }

    @Test
    public void one() {
        drv.navigate().to("http://www.google.com");
        drv.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
    }

    @Test
    public void two() {
        drv.navigate().to("http://www.google.com");
        drv.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
    }

    @Test
    public void three() {
        drv.navigate().to("http://www.google.com");
        drv.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
    }

    @Test
    public void four() {
        drv.navigate().to("http://www.google.com");
        drv.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
    }

    @Test
    public void five() {
        drv.navigate().to("http://www.google.com");
        drv.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
    }

    @Test
    public void six() {
        drv.navigate().to("http://www.google.com");
        drv.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
    }
}
