package com.solvd.qa;

import com.solvd.qa.pages.CatalogPage;
import com.solvd.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryTest extends AbstractTest {

    private static final String CATEGORY_NAME = "Телевизоры";

    @Test
    public void verifyCategoryPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        homePage.getCookiePopUp().clickAcceptCookieButton();
        homePage.clickCatalogButton();
        CatalogPage catalogPage = new CatalogPage(driver);
        Assert.assertTrue(catalogPage.isPageOpened(), "Catalog page is not opened");
        String checkLink = catalogPage.getCategoryLinkByName(CATEGORY_NAME);
        catalogPage.clickOnCategoryByName(CATEGORY_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), checkLink, "Wrong category page");
    }
}
