package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.pages.CommonCheckoutPage;
import com.solvd.qa.gui.desktop.components.ShopPopUp;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CommonCheckoutPage.class)
public class CheckoutPage extends CommonCheckoutPage {

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

    private void checkChooseWHenReceiveCheckBox() {
        waitUntil(ExpectedConditions.elementToBeClickable(chooseWhenReceiveCheckbox.getElement()), MAXIMAL_TIMEOUT);
        try {
            chooseWhenReceiveCheckbox.click();
        } catch (ElementClickInterceptedException e) {
            LOGGER.warn("Element click intercepted, trying to click again");
            JavaScriptUtils.scrollDown(getDriver());
            chooseWhenReceiveCheckbox.click();
        }
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get("url") + "/checkout/");
    }

    public CheckoutPage chooseWhenReceive() {
        try {
            waitUntil(ExpectedConditions.elementToBeClickable(chooseWhenReceiveCheckbox.getElement()), MAXIMAL_TIMEOUT);
            chooseWhenReceiveCheckbox.click();
            return new CheckoutPage(getDriver());
        } catch (ElementClickInterceptedException e) {
            LOGGER.warn("Click intercepted, trying to click once again");
            JavaScriptUtils.scrollDown(getDriver());
            checkChooseWHenReceiveCheckBox();
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
        waitUntil(ExpectedConditions.visibilityOf(chooseShopCheckbox.getElement()), MAXIMAL_TIMEOUT);
        chooseShopCheckbox.click();
        return shopPopUp;
    }

    @Override
    public boolean isOrderFormsComplete() {
        return checkOrderCircle.isElementPresent();
    }

    @Override
    public void fillCheckoutForm() {
        typeName(R.TESTDATA.get("checkout_name")).typePhone(R.TESTDATA.get("phone_number"));
        typeEmail(R.TESTDATA.get("checkout_email"));
        choosePickUpFromShop().chooseShop();
        chooseWhenReceive().choosePaymentWithCashOrCard();
    }
}
