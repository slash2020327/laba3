package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.common.pages.CommonComparePage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import com.solvd.qa.gui.common.pages.CommonSearchPage;
import com.solvd.qa.gui.desktop.pages.ComparePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ComparePageTest extends BaseTest {

    @Test
    public void compareSameBrandNames() {
        CommonHomePage homePage = initPage(getDriver(), CommonHomePage.class);
        homePage.searchProduct(R.TESTDATA.get("brand_name"));
        CommonSearchPage searchPage = initPage(getDriver(), CommonSearchPage.class);
        searchPage.assertPageOpened();
        searchPage.chooseCategoryByName(R.TESTDATA.get("category_name"));
        List<String> productNames = Arrays.asList(R.TESTDATA.get("product_names").split("\\s*,\\s*"));
        searchPage.addProductsToCompare(productNames);
        searchPage.openComparePage();
        CommonComparePage comparePage = initPage(getDriver(), CommonComparePage.class);
        comparePage.assertPageOpened();
        for (String brandName : comparePage.getBrandsRowBrandNames()) {
            Assert.assertEquals(brandName.toLowerCase(), R.TESTDATA.get("brand_name").toLowerCase(), "Brand names don't match");
        }
    }
}
