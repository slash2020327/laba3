package com.solvd.qa;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.qa.gui.components.ProductSlider;
import com.solvd.qa.gui.pages.HomePage;
import com.solvd.qa.utils.JavaScriptUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageTest extends BaseTest {

    @Test
    public void verifySalesHits() {
        HomePage homePage = new HomePage(getDriver());
        JavaScriptUtils.scrollDown(getDriver(), 10);
        ProductSlider salesHits = homePage.getProductSliders().get(0);
        String[] salesHitsNames = R.TESTDATA.get("hits_names").split("\\s*,\\s*");
        for (String salesHitsName : salesHitsNames) {
            Assert.assertEquals(salesHitsName, salesHits.getProductByName(salesHitsName).getProductName());
        }
    }
}
