package com.solvd.qa.gui.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.components.BaseComponent;
import com.solvd.qa.gui.desktop.pages.CheckoutPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutPopUp extends BaseComponent {

    @FindBy (xpath = "//button[@class='btn btn--index btn--lg btn--block']")
    private ExtendedWebElement continueAsGuestButton;

    public CheckoutPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CheckoutPage goToCheckoutAsGuest() {
        continueAsGuestButton.click();
        return new CheckoutPage(getDriver());
    }
}
