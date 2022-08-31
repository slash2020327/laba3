package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.pages.CatalogPage;
import com.solvd.qa.gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryPageTest extends BaseTest {

    @Test
    public void verifyCategoryPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.goToCatalog();
        CatalogPage catalogPage = new CatalogPage(getDriver());
        catalogPage.assertPageOpened();
        String checkLink = catalogPage.getCategoryLinkByName(R.TESTDATA.get("category"));
        catalogPage.clickOnCategoryByName(R.TESTDATA.get("category"));
        Assert.assertEquals(getDriver().getCurrentUrl(), checkLink, "Wrong category page");
    }
}
