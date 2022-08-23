package com.solvd.qa;

import com.solvd.qa.pages.CatalogPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryTest extends AbstractTest {
    private static final String CATEGORY_NAME = "Телевизоры";

    @Test
    public void verifyCategoryPage() {
        driver.get("https://5element.by/catalog");
        CatalogPage catalogPage = new CatalogPage(driver);
        Assert.assertTrue(catalogPage.isPageOpened(), "Catalog page is not opened");
        catalogPage.getCookiePopUp().clickAcceptCookieButton();
        String checkLink = catalogPage.getCategoryLinkByName(CATEGORY_NAME);
        catalogPage.clickOnCategoryByName(CATEGORY_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), checkLink, "Wrong category page");
    }
}
