package session3;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import parallel.TestBase;


public class WindowsAndAlerts extends TestBase {

    @Before
    public void open() {
        driver.get(getTestURL());
    }

    @Test
    public void windowTest() {
        System.out.println("Pos: " + driver.manage().window().getPosition());
        driver.manage().window().setPosition(new Point(100, 100));
        System.out.println("Pos: " + driver.manage().window().getPosition());
        System.out.println("Size: " + driver.manage().window().getSize());
        driver.manage().window().setSize(new Dimension(1000, 140));
        System.out.println("Size: " + driver.manage().window().getSize());
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();


    }


    @Test
    public void alertsTest() {
        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();

        System.out.println("Alert text: " + alert.getText());
        alert.sendKeys("Text to enter");
        alert.accept();
        driver.findElement((By.id("text"))).sendKeys("lalala");
    }

    @Test
    public void iFrameTest() {
        driver.get("http://jsbin.com/bicukojabe/edit?html,output");
        driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe.stretch"))));
        driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("JS Bin Output "))));
        driver.findElement(By.id("test")).sendKeys("Lalalala");
    }
}









//    Alert alert = driver.switchTo().alert();
//
//        System.out.println("Alert text: " + alert.getText());
//                alert.sendKeys("Text to enter");
//                alert.accept();







//        driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe.stretch"))));
//                driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("JS Bin Output "))));
