package com.solvd.qa;

import com.solvd.qa.pages.ComparePage;
import com.solvd.qa.pages.HomePage;
import com.solvd.qa.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

public class CompareTest extends AbstractTest {

    private static final String BRAND_NAME = "Samsung";

    private static final String FIRST_ITEM_NAME = "Смартфон Samsung Galaxy A32 4GB/128GB (фиолетовый)";

    private static final String SECOND_ITEM_NAME = "Смартфон Samsung Galaxy A13 SM-A135FLBKCAU 4GB/128GB (голубой)";

    private static final String THIRD_ITEM_NAME = "Смартфон Samsung Galaxy A33 5G SM-A336BZWGSK 6GB/128GB (белый)";

    @Test()
    public void compareSameBrandNames() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        homePage.getCookiePopUp().clickAcceptCookieButton();
        homePage.getHeaderMenu().clickOnSearchField();
        homePage.getHeaderMenu().searchWith(BRAND_NAME);
        SearchPage searchPage = new SearchPage(driver);
        WaitUtils.pause(1);
        Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");
        searchPage.clickCheckBoxByName("Смартфоны");
        searchPage.getSupportChatDiv().deleteSupportChatFromPage();
        searchPage.clickOnProductCardCompareButtonByName(FIRST_ITEM_NAME);
        searchPage.clickOnProductCardCompareButtonByName(SECOND_ITEM_NAME);
        searchPage.clickOnProductCardCompareButtonByName(THIRD_ITEM_NAME);
        searchPage.getHeaderMenu().clickCompareButton();
        searchPage.getHeaderMenu().getCompareDropdown().clickOnGoToCompareButton();
        ComparePage comparePage = new ComparePage(driver);
        Assert.assertTrue(comparePage.isPageOpened(), "Compare page is not opened");
        Assert.assertEquals(BRAND_NAME.toLowerCase(), comparePage.getBrandsRowBrandNames().get(0));
        Assert.assertEquals(BRAND_NAME.toLowerCase(), comparePage.getBrandsRowBrandNames().get(1));
        Assert.assertEquals(BRAND_NAME.toLowerCase(), comparePage.getBrandsRowBrandNames().get(2));
    }
}
