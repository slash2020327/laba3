package com.solvd.qa.gui.common.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseComponent extends AbstractUIObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseComponent.class);

    public BaseComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
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
}
