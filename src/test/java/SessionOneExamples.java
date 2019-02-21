
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;

public class SessionOneExamples {

    WebDriver drv1,drv2;

    @Before
    public  void start()
    {   WebDriverManager.iedriver().arch32().setup();

        drv1 = new InternetExplorerDriver();
        drv2 = new InternetExplorerDriver();
    }

    @Test
    public void cookieTest() {
        //drv1.manage().addCookie(new Cookie("test", "test"));

        drv1.get("http://google.com");
        drv1.manage().addCookie(new Cookie("test", "test"));

        drv2.get("http://google.com");
        System.out.println("Drv1 before: " + drv1.manage().getCookies());
        System.out.println("Drv2 before: " + drv2.manage().getCookies());

        drv1.manage().addCookie(new Cookie("Security_ID", "KJILSDuf984jmmcoidshnfrdoiut98e"));
        System.out.println("Drv1 after: " + drv1.manage().getCookies());
        System.out.println("Drv1 NID: " + drv1.manage().getCookieNamed("NID"));

        drv1.manage().deleteCookieNamed("NID");
        System.out.println("Drv1 NID deleted: " + drv1.manage().getCookies());

        drv1.manage().deleteAllCookies();
        System.out.println("Drv1 delete: " + drv1.manage().getCookies());
        System.out.println("Drv2 delete: " + drv2.manage().getCookies());
    }

    @After
    public void stop()
    {
        drv1.quit();
        drv2.quit();
    }


}
