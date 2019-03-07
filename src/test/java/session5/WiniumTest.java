package session5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WiniumTest {

    WebDriver driver;

    @Before
    public void start() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", "C:\\Windows\\System32\\calc.exe");

        driver = new RemoteWebDriver(new URL("http://localhost:9999"), caps);
    }

    @Test
    public void test() {

        driver.findElement(By.name("One")).click();
        driver.findElement(By.name("Two")).click();
        driver.findElement(By.name("Plus")).click();
        driver.findElement(By.name("Four")).click();
        driver.findElement(By.name("Six")).click();
        driver.findElement(By.name("Equals")).click();

        String value = driver.findElement(By.id("CalculatorResults")).getAttribute("Name");

        Assert.assertEquals("Display is 58", value);
    }

    @After
    public void stop() {
        driver.findElement(By.id("Close")).click();
    }
}
