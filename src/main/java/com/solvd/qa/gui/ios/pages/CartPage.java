package com.solvd.qa.gui.ios.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CommonCartPage.class)
public class CartPage extends CommonCartPage {

    @FindBy(xpath = "//div[@class='c-part']/div[@class='c-cost']")
    private List<ExtendedWebElement> prices;

    @FindBy(xpath = "//td[@class='ec-total-value']")
    private ExtendedWebElement total;

    @FindBy(xpath = "//h1[text()='Моя корзина ']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//span[text()='Оформить заказ']/ancestor::button")
    private ExtendedWebElement goToCheckoutButton;

    @FindBy(xpath = "//span[text()='Продолжить как гость']/ancestor::button")
    private ExtendedWebElement goToCheckoutAsGuestButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(title);
    }

    @Override
    public BigDecimal getSumOfPrices() {
        BigDecimal sum = BigDecimal.ZERO;
        for (ExtendedWebElement price : prices) {
            sum = sum.add(new BigDecimal(price.getText().replaceAll("\\s", "")));
        }
        return sum;
    }

    @Override
    public BigDecimal getTotal() {
        return new BigDecimal(this.total.getText());
    }

    @Override
    public void goToCheckout() {
        goToCheckoutButton.click();
        waitUntil(ExpectedConditions.visibilityOf(goToCheckoutAsGuestButton.getElement()), MAXIMAL_TIMEOUT);
        goToCheckoutAsGuestButton.click();
    }
}
