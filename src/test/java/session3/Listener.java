package session3;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Listener extends AbstractWebDriverEventListener {

//    @Override
//    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//        System.out.println("[Start search for:] " + by);
//    }
//
//    @Override
//    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//        System.out.println("["+ by + "] has been found");
//    }
//
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println("[Shit Happened:] "+throwable.getMessage().split(":")[0]);


        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile, new File(System.currentTimeMillis() + "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[Screenshot captured]");
    }


/////////////////////


    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        highlight(element, "orange",driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        highlight(element, "red", driver);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        highlight(element, "green", driver);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        highlight(element, "orange", driver);
    }

    public static <T extends WebElement> T highlight(T element , WebDriver driver) {
        return highlight(element, "orange", driver);
    }

    public static <T extends WebElement> T highlight(T element, final String color, WebDriver driver) {
        if (element != null && element.getAttribute("__selenideHighlighting") == null) {
            for (int i = 1; i < 4; i++) {
                transform(element, color, i, driver);
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 4; i > 0; i--) {
                transform(element, color, i, driver);
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return element;
    }

    private static void transform(WebElement element, String color, int i, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].setAttribute('__selenideHighlighting', 'done'); " +
                        "arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: " + i + "px solid " + color + "; border-style: dotted; " +
                        "transform: scale(1, 1." + i + ");");
    }

}
