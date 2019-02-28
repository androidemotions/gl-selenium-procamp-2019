package session3;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import parallel.TestBase;

import java.util.List;

public class MutableInteractions extends TestBase {

    @Test
    public void selectTest() {
        By select = By.name("currency_code");

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("a.fancybox-region")).click();

        wait.until(ExpectedConditions.elementToBeClickable(select));

        Select curr = new Select(driver.findElement(select));

        curr.selectByIndex(2);
        curr.selectByValue("USD");
    }

    @Test
    public void actionsTest() {

        driver.navigate().to("http://jqueryui.com/resources/demos/sortable/connect-lists.html");

        List<WebElement> firstList = driver.findElements(By.cssSelector("#sortable1 li"));
        List<WebElement> secondList = driver.findElements(By.cssSelector("#sortable2 li"));

        new Actions(driver)
                .dragAndDrop(firstList.get(0), firstList.get(3))
                .perform();
        new Actions(driver)
                .dragAndDrop(firstList.get(0), secondList.get(2))
                .perform();

        Action moveDown = new Actions(driver)
                .moveToElement(firstList.get(1))
                .clickAndHold()
                .moveByOffset(0, 50)
                .release().build();

        moveDown.perform();
        moveDown.perform();
        moveDown.perform();
    }


    @Test
    public void invisibleTest() {

        driver.navigate().to("http://cssglobe.com/lab/style_select/01.html");
        WebElement selectEl = driver.findElement(By.cssSelector("select"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 3;", selectEl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 3; arguments[0].dispatchEvent(new Event('change'))", selectEl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.opacity=1", selectEl);
        Select select = new Select(selectEl);
        select.selectByIndex(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.opacity=0", selectEl);
    }
}
