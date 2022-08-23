package com.solvd.qa.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CookiesPopUp extends AbstractComponent {

    @FindBy(xpath = "//button[@class='js-cookie-popup-close btn btn--block']")
    private WebElement acceptCookiesButton;

    public CookiesPopUp(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptCookieButton() {
        acceptCookiesButton.click();
    }
}
