package session4;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import parallel.TestBase;

import java.util.logging.Level;

public class BrowserLogs extends TestBase {

    WebDriver drv;

    @Test
    public void simpleLogs() {
        System.out.println(driver.manage().logs().getAvailableLogTypes());

        driver.get("https://www.facebook.com/");

        String[] logTypes = {"browser", "driver", "client"};

        for (String logType : logTypes) {
            for (LogEntry l : driver.manage().logs().get(logType).getAll()) {
                System.out.println(logType + ":" + l);
            }
        }

    }

    @Test
    public void performanceLogs() {
        ChromeOptions opt = new ChromeOptions();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        opt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        drv = new ChromeDriver(opt);

        System.out.println(drv.manage().logs().getAvailableLogTypes());


        drv.get("http://localhost/litecart/en");

        for (LogEntry entry : drv.manage().logs().get(LogType.PERFORMANCE)) {
            System.out.println(entry.toString());
        }
    }

    @After
    public void stopLocalDriver() {
        if (drv != null) {
            drv.quit();
            drv = null;
        }
    }
}
