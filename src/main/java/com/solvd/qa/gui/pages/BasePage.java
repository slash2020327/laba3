package com.solvd.qa.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.qa.gui.components.CartPopUp;
import com.solvd.qa.gui.components.HeaderMenu;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    @FindBy(xpath = "//div[@class='h-panel-hide']")
    private HeaderMenu headerMenu;

    @FindBy(xpath = "//div[@class='modal-popup modal-added']")
    private CartPopUp cartPopUp;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }

    public void tryToClickIntercepted(ExtendedWebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            LOGGER.warn("Click intercepted, trying to click once again");
            JavaScriptUtils.scrollUp(getDriver());
            JavaScriptUtils.hideHeader(getDriver());
            JavaScriptUtils.hideSupportChat(getDriver());
            element.click();
        } finally {
            JavaScriptUtils.showHeader(getDriver());
            JavaScriptUtils.showSupportChat(getDriver());
        }
    }

    public CartPopUp getCartPopUp() {
        //TODO Fix
        pause(1);
        return cartPopUp;
    }
}
