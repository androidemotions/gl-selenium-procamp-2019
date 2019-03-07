package session5;

import com.codeborne.selenide.SelenideElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideTest {

    @Before
    public void start() {
        System.setProperty("selenide.browser", "firefox");
    }

    @Test
    public void searchAndOpenSelenideSite() {
        open("http://google.com");
        $(By.name("q")).sendKeys("selenide" + Keys.ENTER);
        $("h3").click();
        $("div.news div.news-line").shouldHave(text("Вышла Selenide 5.1.0"));
    }







    @Test
    public void liteCartMenuTest(){
        SelenideElement menuBlock = $("#sidebar ul#box-apps-menu");
        String selectedItem = "li.selected";

        open("http://localhost/litecart/admin");
        $("input[name=username]").sendKeys("admin");
        $("input[name=password]").sendKeys("admin");
        $("[type=submit]").click();

        int mainMenuSize = menuBlock.$$("li").size();

        for (int i = 1; i <= mainMenuSize; i++){
            menuBlock.$(By.xpath("./li[" + i + "]")).click();
            $$("h1").shouldHaveSize(1);
            int subMenuSize = menuBlock.$(selectedItem).$$("li").size();
            if (subMenuSize > 0) {
                for (int j = 1; j <=subMenuSize; j++) {
                    $(selectedItem).$("li:nth-of-type(" + j + ")").click();
                    $$("h1").shouldHaveSize(1);
                }
            }
        }
    }
}
