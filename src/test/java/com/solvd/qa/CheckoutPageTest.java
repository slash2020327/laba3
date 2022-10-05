package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.common.pages.CommonCartPage;
import com.solvd.qa.gui.common.pages.CommonCheckoutPage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import com.solvd.qa.gui.common.pages.CommonSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CheckoutPageTest extends BaseTest {

    @Test
    public void verifyCheckoutProcess() {
        CommonHomePage homePage = initPage(getDriver(), CommonHomePage.class);
        homePage.assertPageOpened();
        homePage.searchProduct(R.TESTDATA.get("checkout_product"));
        CommonSearchPage searchPage = initPage(getDriver(), CommonSearchPage.class);
        searchPage.assertPageOpened();
        List<String> codes = Arrays.asList(R.TESTDATA.get("checkout_product_code"));
        searchPage.addProductsToCardByProductCodes(codes);
        searchPage.openCart();
        CommonCartPage cartPage = initPage(getDriver(), CommonCartPage.class);
        cartPage.assertPageOpened();
        cartPage.goToCheckout();
        CommonCheckoutPage checkoutPage = initPage(getDriver(), CommonCheckoutPage.class);
        checkoutPage.assertPageOpened();
        checkoutPage.fillCheckoutForm();
        Assert.assertTrue(checkoutPage.isOrderFormsComplete(), "Order form is not completed");

    }
}
