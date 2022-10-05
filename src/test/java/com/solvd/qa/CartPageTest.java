package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.common.pages.CommonCartPage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import com.solvd.qa.gui.common.pages.CommonSearchPage;
import com.solvd.qa.gui.desktop.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CartPageTest implements IAbstractTest {

    /* Due to bug in Carina 7.4.25.1810-SNAPSHOT mobile devices drivers
     couldn't access chrome args through config.properties
      so in this test it is necessary to set up in a different way */

    protected WebDriver driver;

    private static final String CART_PAGE_DRIVER = "cartPageDriver";
    private static final String DEFAULT_DRIVER = "default";


    @BeforeMethod
    public void cartPageTestSetup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-features=Translate");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        String platformName = R.CONFIG.get("capabilities.platformName");
        String browserName = R.CONFIG.get("capabilities.browserName");
        if (platformName.equals("ANDROID") && browserName.equals("chrome")) {
            capabilities.setCapability(CapabilityType.PLATFORM_NAME, SpecialKeywords.ANDROID);
            getDriver(DEFAULT, capabilities);
        }
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        homePage.getCookiesPopUp().clickAcceptCookieButton();
    }

    @Test
    public void verifyTotalCost() {
        CommonHomePage homePage = initPage(getDriver(), CommonHomePage.class);
        homePage.searchProduct(R.TESTDATA.get("product_search"));
        CommonSearchPage searchPage = initPage(getDriver(), CommonSearchPage.class);
        searchPage.assertPageOpened();
        List<String> productCodes = Arrays.asList(R.TESTDATA.get("product_codes").split("\\s*,\\s*"));
        searchPage.addProductsToCardByProductCodes(productCodes);
        searchPage.openCart();
        CommonCartPage cartPage = initPage(getDriver(), CommonCartPage.class);
        cartPage.assertPageOpened();
        Assert.assertEquals(cartPage.getSumOfPrices(), cartPage.getTotal());
    }
}
