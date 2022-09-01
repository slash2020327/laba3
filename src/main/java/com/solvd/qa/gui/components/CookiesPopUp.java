package com.solvd.qa.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CookiesPopUp extends BaseComponent {

    @FindBy(xpath = "//button[@class='js-cookie-popup-close btn btn--block']")
    private ExtendedWebElement acceptCookiesButton;

    public CookiesPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickAcceptCookieButton() {
        acceptCookiesButton.click();
    }
}
