package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.pages.ComparePage;
import com.solvd.qa.gui.pages.HomePage;
import com.solvd.qa.gui.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComparePageTest extends BaseTest {

    @Test
    public void compareSameBrandNames() {
        HomePage homePage = new HomePage(getDriver());
        homePage.getHeaderMenu().searchWith(R.TESTDATA.get("brand_name"));
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.assertPageOpened();
        searchPage.clickCheckBoxByName(R.TESTDATA.get("category_name"));
        String[] productNames = R.TESTDATA.get("product_names").split("\\s*,\\s*");
        for (String productName : productNames) {
            searchPage.getProductByName(productName).addToCompare();
        }
        searchPage.getHeaderMenu().openCompareDropDown();
        searchPage.getHeaderMenu().getCompareDropDown().openComparePage();
        ComparePage comparePage = new ComparePage(getDriver());
        comparePage.assertPageOpened();
        for (String brandName : comparePage.getBrandsRowBrandNames()) {
            Assert.assertEquals(R.TESTDATA.get("brand_name").toLowerCase(), brandName.toLowerCase(), "Brand names don't match");
        }
    }
}
