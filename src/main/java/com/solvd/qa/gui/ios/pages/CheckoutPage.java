package com.solvd.qa.gui.ios.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonCheckoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CommonCheckoutPage.class)
public class CheckoutPage extends CommonCheckoutPage {

    @FindBy(xpath = "//h3[contains(text(), '1. Введите данные получателя заказа')]")
    private ExtendedWebElement firstOrderHeading;

    @FindBy(xpath = "//div[@class='checkout-order-heading']")
    private ExtendedWebElement orderHeading;

    @FindBy(xpath = "//label[text()='Имя']/following-sibling::input")
    private ExtendedWebElement nameField;

    @FindBy(xpath = "//label[text()='Номер телефона']/following-sibling::input")
    private ExtendedWebElement phoneNumberField;

    @FindBy(xpath = "//span[text()='Ввести данные о доставке']/ancestor::button")
    private ExtendedWebElement inputDeliveryDataButton;

    @FindBy(xpath = "//b[contains(text(), 'Выбрать')]/ancestor::div[@class='inp-box']/label")
    private ExtendedWebElement chooseShopCheckBox;

    @FindBy(xpath = "//span[text()='Выбрать']/ancestor::button")
    private ExtendedWebElement chooseShopButton;

    @FindBy(xpath = "//span[text()='Ввести данные об оплате']/ancestor::button")
    private ExtendedWebElement inputPaymentDataButton;

    @FindBy(xpath = "//b[contains(text(), 'При получении')]/ancestor::div[@class='inp-box']/label")
    private ExtendedWebElement whenReceiveCheckBox;

    @FindBy(xpath = "//b[contains(text(), 'Наличными')]/ancestor::div[@class='inp-box']/label")
    private ExtendedWebElement cashOrCardCheckBox;

    @FindBy(xpath = "//span[text()='Продолжить']/ancestor::button")
    private ExtendedWebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(firstOrderHeading);
    }

    private void typeName(String name) {
        nameField.type(name);
    }

    private void typePhone(String phone) {
        phoneNumberField.type(phone);
    }

    private void clickInputDeliveryDataButton() {
        inputDeliveryDataButton.click();
    }

    private void checkChooseShopCheckBox() {
        chooseShopCheckBox.click();
    }

    private void clickChooseShopButton() {
        waitUntil(ExpectedConditions.elementToBeClickable(chooseShopButton.getElement()), MAXIMAL_TIMEOUT);
        chooseShopButton.click();
    }

    private void clickInputPaymentDataButton() {
        waitUntil(ExpectedConditions.elementToBeClickable(inputPaymentDataButton.getElement()), MAXIMAL_TIMEOUT);
        inputPaymentDataButton.click();
    }

    private void clickContinueButton() {
        waitUntil(ExpectedConditions.elementToBeClickable(continueButton.getElement()), MAXIMAL_TIMEOUT);
        continueButton.click();
    }

    private void checkWhenReceiveCheckBox() {
        whenReceiveCheckBox.click();
    }

    private void checkCashOrCardCheckBox() {
        cashOrCardCheckBox.click();
    }

    @Override
    public void fillCheckoutForm() {
        typeName(R.TESTDATA.get("checkout_name"));
        typePhone(R.TESTDATA.get("phone_number"));
        clickInputDeliveryDataButton();
        checkChooseShopCheckBox();
        clickChooseShopButton();
        clickInputPaymentDataButton();
        checkWhenReceiveCheckBox();
        checkCashOrCardCheckBox();
        clickContinueButton();
    }

    @Override
    public boolean isOrderFormsComplete() {
        return orderHeading.isElementPresent(MAXIMAL_TIMEOUT);
    }
}
