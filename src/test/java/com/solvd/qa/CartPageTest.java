package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.pages.CartPage;
import com.solvd.qa.gui.pages.HomePage;
import com.solvd.qa.gui.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    @Test
    public void verifyTotalCost() {
        HomePage homePage = new HomePage(getDriver());
        homePage.getHeaderMenu().searchWith(R.TESTDATA.get("product_search"));
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.assertPageOpened();
        String[] productCodes = R.TESTDATA.get("product_codes").split("\\s*,\\s*");
        for (String productCode : productCodes) {
            searchPage.getProductByCode(Integer.parseInt(productCode)).addToCart();
            searchPage.getCartPopUp().backToShopping();
        }
        searchPage.getHeaderMenu().openCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.assertPageOpened();
        Assert.assertEquals(cartPage.getSumOfPrices(), cartPage.getTotal());
    }
}
