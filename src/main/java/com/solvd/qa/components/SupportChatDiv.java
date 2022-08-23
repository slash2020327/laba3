package com.solvd.qa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class SupportChatDiv extends AbstractComponent {

    private By supportChatDivPath = By.xpath("//jdiv[@class='hoverl_bdff']");

    public SupportChatDiv(WebDriver driver) {
        super(driver);
    }

    public void deleteSupportChatDiv() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(supportChatDivPath));
        js.executeScript("return document.getElementsByTagName('jdiv')[0].remove();");
    }
}
