package session4;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProxyTest {
    WebDriver driver;
    WebDriverWait wait;
    BrowserMobProxy proxy;

    @Before
    public void start() {
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        ChromeOptions opt = new ChromeOptions();
        opt.setCapability(CapabilityType.PROXY, seleniumProxy);

        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver,5);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    }

    @Test
    public void proxyTest() {
        proxy.newHar("google");

        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium" + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h3"))).click();


        Har har = proxy.getHar();

        for (HarEntry entry: har.getLog().getEntries())
        {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println("My_Log: "+request.getUrl()+" : "+
                    response.getStatus() +  " : " + response.getContent().getText() +" : "+
                    entry.getTime() +"ms");
        }

    }

    @After
    public void stop() {
        driver.quit();
        proxy.stop();
    }

}
