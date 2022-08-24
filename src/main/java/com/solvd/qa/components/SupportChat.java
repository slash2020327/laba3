package com.solvd.qa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class SupportChat extends AbstractComponent {

    private By supportChatDivPath = By.xpath("//jdiv[@class='hoverl_bdff' or @class='hoverl_b936']");

    public SupportChat(WebDriver driver) {
        super(driver);
    }

    public void deleteSupportChatFromPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(supportChatDivPath));
        js.executeScript("return document.getElementsByTagName('jdiv')[0].remove();");
    }
}
