package session4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.TestBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventsListenerTest extends TestBase {

    EventFiringWebDriver edr;
    WebDriverWait wait;

    @Before
    public void start() {
//        ChromeOptions opt = new ChromeOptions();
//        opt.setHeadless(true);
        edr = new EventFiringWebDriver(new ChromeDriver());
        edr.register(new Listener());

        wait = new WebDriverWait(edr,5);
    }

    @After
    public void stop() {
        edr.quit();
    }

    @Test
    public void listenerTest() {
        By addNewProductButton = By.xpath("//a[@class='button'][2]");

        edr.get("http://localhost/litecart/admin");
        edr.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        edr.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        edr.findElement(By.cssSelector("button[name=login]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#app-")));

        edr.findElement(By.xpath("//li[@id='app-'][2]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(addNewProductButton));

        edr.findElement(addNewProductButton).click();
        edr.findElement(By.xpath("//label[1]/input[@name='status']")).click();
        String duckName = "Duck_" + String.valueOf(System.currentTimeMillis());
        edr.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(duckName);
        edr.findElements(By.cssSelector("input[name='product_groups[]']")).get(2).click();
        edr.findElement(By.cssSelector("input[name='quantity']")).clear();
        edr.findElement(By.cssSelector("input[name='quantity']")).sendKeys("12");
        edr.findElement(By.cssSelector("input[name='date_valid_from']"))
                .sendKeys(new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime()));
        edr.findElement(By.cssSelector("input[name='date_valid_to']"))
                .sendKeys(new SimpleDateFormat("MMdd").format(Calendar.getInstance().getTime()) + "2022");

        //wrong locator
        edr.findElement(By.cssSelector("missedTag")).click();
    }


}
