package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.qa.gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class BaseTest implements IAbstractTest {

    @BeforeMethod
    public void setup(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        homePage.getCookiesPopUp().clickAcceptCookieButton();
    }
}
