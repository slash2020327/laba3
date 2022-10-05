package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.desktop.components.CartPopUp;
import com.solvd.qa.gui.desktop.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class BaseTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @DataProvider(parallel = true, name = "capabilitiesDataProvider")
    public static Object[][] capabilitiesDataProvider() {
        return new Object[][] {
                { "Nexus_6", "ANDROID", "chrome" },
                { "iPhone 7 Plus", "iOS", "safari" }
        };
    }

    @BeforeMethod()
    public void setup() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        try {
            homePage.getCookiesPopUp().clickAcceptCookieButton();
        } catch (Exception e) {
            LOGGER.warn("CookiePopUp not found, trying find it and close after page refresh");
            getDriver().navigate().refresh();
            homePage.getCookiesPopUp().clickAcceptCookieButton();
        }

    }
}

