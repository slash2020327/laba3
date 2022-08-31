package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.pages.CartPage;
import com.solvd.qa.gui.pages.CheckoutPage;
import com.solvd.qa.gui.pages.HomePage;
import com.solvd.qa.utils.JavaScriptUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutPageTest extends BaseTest{

    @Test
    public void verifyCheckoutProcess(){
        HomePage homePage = new HomePage(getDriver());
        homePage.assertPageOpened();
        JavaScriptUtils.scrollDown(getDriver(), 10);
        homePage.getProductByName(R.TESTDATA.get("checkout_product")).addToCart();
        homePage.getCartPopUp().goToCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.assertPageOpened();
        cartPage.goToCheckout();
        cartPage.getCheckoutPopUp().goToCheckoutAsGuest();
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.assertPageOpened();
        checkoutPage.typeName(R.TESTDATA.get("checkout_name")).typePhone(R.TESTDATA.get("phone_number"));
        checkoutPage.typeEmail(R.TESTDATA.get("checkout_email"));
        checkoutPage.choosePickUpFromShop().chooseShop();
        checkoutPage.chooseWhenReceive().choosePaymentWithCashOrCard();
        Assert.assertTrue(checkoutPage.orderStatusCheck());

    }
}