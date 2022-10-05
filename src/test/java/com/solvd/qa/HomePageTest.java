package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;


public class HomePageTest extends BaseTest {

    @Test
    public void verifySalesHits() {
        CommonHomePage homePage = initPage(getDriver(), CommonHomePage.class);
        homePage.assertPageOpened();
        homePage.moveToSalesHits();
        Set<String> salesHitsNames = Set.of(R.TESTDATA.get("hits_names").split("\\s*,\\s*"));
        Assert.assertEquals(homePage.getSalesHitsNames(), salesHitsNames);
    }
}
