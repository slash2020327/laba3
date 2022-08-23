package com.solvd.qa.pages;

import com.solvd.qa.components.HeaderMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@href='/catalog/']")
    private WebElement catalogButton;

    public HomePage(WebDriver driver) {
        super(driver);
        expectedPageUrl = configFileReader.getValueByKey("url");
        PageFactory.initElements(driver, this);
    }

    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(driver);
    }

    public void clickCatalogButton(){
        catalogButton.click();
    }
}
