package session3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import parallel.TestBase;

import java.util.concurrent.TimeUnit;


public class ImmutableInteractions extends TestBase {

    WebDriver ff, ie, cr;

    @Before
    public void open() {
        driver.get(getTestURL());
    }

    @Test
    public void getAttributeTest() {

        WebElement textInput = driver.findElement(By.id("text"));
        WebElement link = driver.findElement(By.id("link"));
        Select select = new Select(driver.findElement(By.name("currency_code")));


        textInput.sendKeys("Test Message");
        System.out.println("GetText: " + textInput.getText());
        System.out.println("GetAttr: " + textInput.getAttribute("value"));

        System.out.println("GetLink: " + link.getAttribute("href"));

        select.selectByIndex(0);
        System.out.println("Select: " + select.getFirstSelectedOption().getAttribute("selected"));

        for (WebElement selected : select.getOptions()) {
            System.out.println("Selected: " +
                    selected.getText() + " : " +
                    selected.getAttribute("selected"));
        }
    }

    @Test
    public void getTextTest() {

        WebElement invisible = driver.findElement(By.id("invisible"));
        WebElement spaces = driver.findElement(By.id("spaces"));
        WebElement spacespre = driver.findElement(By.id("spacespre"));
        WebElement div = driver.findElement(By.id("div"));

        System.out.println("GetInvisibleText: " + invisible.getText());
        System.out.println("GetInvisibleAttr: " + invisible.getAttribute("textContent"));

        System.out.println("GetTextForDiv: " + driver.findElement(By.id("div")).getText());

        System.out.println("GetSpacesText: " + spaces.getText());
        System.out.println("GetSpacesAttr: " + spaces.getAttribute("textContent"));

        System.out.println("GetSpacesPreText: " + spacespre.getText());
        System.out.println("GetSpacesPreAttr: " + spacespre.getAttribute("textContent"));

        System.out.println("GetTextForNestedElements: " + div.getText());
        System.out.println("GetAttrForNestedElements: " + div.getAttribute("textContent"));
    }

    @Test
    public void isDisplayedTest() {
        driver.get("https://output.jsbin.com/saqoca/2");
        System.out.println("transparent: " + driver.findElement(By.id("transparent")).isDisplayed());
        System.out.println("white: " + driver.findElement(By.id("white")).isDisplayed());
        System.out.println("behind: " + driver.findElement(By.id("behind")).isDisplayed());
        System.out.println("outside: " + driver.findElement(By.id("outside")).isDisplayed());
        System.out.println("shifted: " + driver.findElement(By.id("shifted")).isDisplayed());
    }


    @Test
    public void getCssValues() {
        cssDriversStart();

        By text = By.cssSelector("strong.campaign-price");

        WebElement fftext = ff.findElement(text);
        WebElement ietext = ie.findElement(text);
        WebElement crtext = cr.findElement(text);

        System.out.println("FF color: " + fftext.getCssValue("color"));
        System.out.println("CR color: " + crtext.getCssValue("color"));
        System.out.println("IE color: " + ietext.getCssValue("color"));

        System.out.println("FF bk_color: " + fftext.getCssValue("background-color"));
        System.out.println("CR bk_color: " + crtext.getCssValue("background-color"));
        System.out.println("IE bk_color: " + ietext.getCssValue("background-color"));

        System.out.println("FF font: " + fftext.getCssValue("font"));
        System.out.println("CR font: " + crtext.getCssValue("font"));
        System.out.println("IE font: " + ietext.getCssValue("font"));

        System.out.println("FF font-family: " + fftext.getCssValue("font-family"));
        System.out.println("CR font-family: " + crtext.getCssValue("font-family"));
        System.out.println("IE font-family: " + ietext.getCssValue("font-family"));

        System.out.println("FF font-weight: " + fftext.getCssValue("font-weight"));
        System.out.println("CR font-weight: " + crtext.getCssValue("font-weight"));
        System.out.println("IE font-weight: " + ietext.getCssValue("font-weight"));

    }

    private void cssDriversStart() {

        ff = new FirefoxDriver();
        ff.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverManager.iedriver().arch32().setup();
        ie = new InternetExplorerDriver();
        ie.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        cr = driver;
        cr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String url = "http://localhost/litecart/en/";

        ff.get(url);
        ie.get(url);
        cr.get(url);
    }

    @After
    public void cssStop() {
        if (ff != null) ff.quit();
        if (ie != null) ie.quit();
        if (cr != null) cr.quit();
    }
}
