package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import com.solvd.qa.gui.desktop.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryPageTest extends BaseTest {

    @Test
    public void verifyCategoryPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.goToCatalog();
        CommonCatalogPage catalogPage = initPage(getDriver(), CommonCatalogPage.class);
        catalogPage.assertPageOpened();
        String checkLink = catalogPage.getCategoryLinkByName(R.TESTDATA.get("category"));
        catalogPage.clickOnCategoryByName(R.TESTDATA.get("category"));
        Assert.assertEquals(getDriver().getCurrentUrl(), checkLink, "Wrong category page");
    }
}
