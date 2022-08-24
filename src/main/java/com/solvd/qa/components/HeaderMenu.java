package com.solvd.qa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class HeaderMenu extends AbstractComponent {
    @FindBy(xpath = "//*[@placeholder='Поиск товара']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@type='text' and @placeholder='Поиск товара' and @maxlength='90' ]")
    private WebElement hiddenSearchField;

    @FindBy(xpath = "//div[text()='Сравнение']/ancestor::div[@class='h-added-drop h-drop js-drop-select']")
    private WebElement compareButton;

    public HeaderMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HeaderMenu clickOnSearchField() {
        searchField.click();
        return new HeaderMenu(driver);
    }

    public HeaderMenu searchWith(String search) {
        hiddenSearchField.sendKeys(search);
        hiddenSearchField.submit();
        return new HeaderMenu(driver);
    }

    public HeaderMenu clickCompareButton() {
        WaitUtils.pause(1);
        compareButton.click();
        return new HeaderMenu(driver);
    }

    public CompareDropdown getCompareDropdown() {
        return PageFactory.initElements(driver, CompareDropdown.class);
    }
}
