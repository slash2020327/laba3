package com.solvd.qa.gui.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.components.ShopPopUp;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPage extends PageWithProductCards {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutPage.class);

    @FindBy(xpath = "//li[@class='checkout-step checkout-step--current']/div[text()='Проверка заказа']")
    private ExtendedWebElement checkOrderCircle;

    @FindBy(xpath = "//input[@id='name']")
    private ExtendedWebElement nameField;

    @FindBy(xpath = "//input[@id='phone']")
    private ExtendedWebElement phoneField;

    @FindBy(xpath = "//input[@id='email']")
    private ExtendedWebElement emailField;

    @FindBy(xpath = "//b[contains(text(), 'Выбрать')]//ancestor::label")
    private ExtendedWebElement chooseShopCheckbox;

    @FindBy(xpath = "//div[@class='inp-box__text' and text()='При получении']//ancestor::label")
    private ExtendedWebElement chooseWhenReceiveCheckbox;

    @FindBy(xpath = "//div[@class='inp-box__text' and text()='Наличными или банковской картой']//ancestor::label")
    private ExtendedWebElement choosePaymentWithCashOrCardCheckbox;

    @FindBy(xpath = "//div[@class='modal-popup modal-checkout-stores']")
    private ShopPopUp shopPopUp;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get("url") + "/checkout/");
    }

    public CheckoutPage chooseWhenReceive() {
        try {
            chooseWhenReceiveCheckbox.click();
            return new CheckoutPage(getDriver());
        } catch (ElementClickInterceptedException e) {
            LOGGER.warn("Click intercepted, trying to click once again");
            JavaScriptUtils.scrollDown(getDriver());
            chooseWhenReceiveCheckbox.click();
            return new CheckoutPage(getDriver());
        }
    }

    public CheckoutPage choosePaymentWithCashOrCard() {
        choosePaymentWithCashOrCardCheckbox.click();
        return new CheckoutPage(getDriver());
    }

    public ShopPopUp getShopPopUp() {
        return shopPopUp;
    }

    public CheckoutPage typeName(String name) {
        nameField.type(name);
        return new CheckoutPage(getDriver());
    }

    public CheckoutPage typePhone(String phone) {
        phoneField.type(phone);
        return new CheckoutPage(getDriver());
    }

    public CheckoutPage typeEmail(String email) {
        emailField.type(email);
        return new CheckoutPage(getDriver());
    }

    public ShopPopUp choosePickUpFromShop() {
        chooseShopCheckbox.click();
        return shopPopUp;
    }

    public boolean orderStatusCheck() {
        return checkOrderCircle.isElementPresent();
    }
}
