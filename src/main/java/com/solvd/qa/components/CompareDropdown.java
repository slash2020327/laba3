package com.solvd.qa.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class CompareDropdown extends AbstractComponent {

    @FindBy(xpath = "//a[contains(text(), 'Перейти в сравнение')]")
    private WebElement goToComparePageButton;

    public CompareDropdown(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CompareDropdown clickOnGoToCompareButton() {
        WaitUtils.pause(1);
        goToComparePageButton.click();
        return new CompareDropdown(driver);
    }

}
