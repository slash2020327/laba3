package com.solvd.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class SearchPage extends AbstractPage {

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = "(//div[@class='filter-line']/button) | (//a[@aria-label='change-view']) ")
    private WebElement changeViewButton;

    private static final String CHECKBOX_LOCATOR = ".//following::div[text()='%s ' or text()='%s']";

    public SearchPage(WebDriver driver) {
        super(driver);
        expectedPageUrl = configFileReader.getValueByKey("url") + "/search/";
        PageFactory.initElements(driver, this);
    }

    public void clickChangeViewButton() {
        changeViewButton.click();
    }

    public void clickCheckBoxByName(String name) {
        String checkBoxPath = String.format(CHECKBOX_LOCATOR, name, name);
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(checkBoxPath)));
        checkBox.findElement(By.xpath(checkBoxPath)).click();
    }
}
