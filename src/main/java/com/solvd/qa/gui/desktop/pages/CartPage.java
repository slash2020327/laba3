package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonCartPage;
import com.solvd.qa.gui.desktop.components.CheckoutPopUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CommonCartPage.class)
public class CartPage extends CommonCartPage {

    @FindBy(xpath = "//div[@class='section-product']//div[@class='c-cost']")
    private List<ExtendedWebElement> prices;

    @FindBy(xpath = "//div[@class='shopping-order-total__value ec-total-value']")
    private ExtendedWebElement total;

    @FindBy(xpath = "//div[@class='section-heading__title']")
    private ExtendedWebElement cartTitle;

    @FindBy(xpath = "//div[@class='modal-popup modal-login-order']")
    private CheckoutPopUp checkoutPopUp;

    @FindBy(xpath = "//a[@class='btn btn--block btn--lg']")
    private ExtendedWebElement goToCheckOutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(cartTitle);
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
        return new BigDecimal(this.total.getText().replaceAll("\\s", ""));
    }

    public CheckoutPopUp getCheckoutPopUp() {
        return checkoutPopUp;
    }

    @Override
    public void goToCheckout() {
        goToCheckOutButton.click();
        getCheckoutPopUp().goToCheckoutAsGuest();
    }

}
