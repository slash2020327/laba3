package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryPageTest extends BaseTest {

    @Test
    public void verifyCategoryPage() {
        CommonHomePage homePage = initPage(getDriver(), CommonHomePage.class);
        CommonCatalogPage catalogPage = homePage.goToCatalog();
        catalogPage.assertPageOpened();
        String checkLink = catalogPage.getCategoryLinkByName(R.TESTDATA.get("category"));
        catalogPage.clickOnCategoryByName(R.TESTDATA.get("category"));
        Assert.assertEquals(checkLink, getDriver().getCurrentUrl(), "Wrong category page");
    }
}
