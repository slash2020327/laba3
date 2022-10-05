package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import com.solvd.qa.gui.desktop.components.CookiesPopUp;
import com.solvd.qa.gui.desktop.components.ProductSlider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;


@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CommonHomePage.class)
public class HomePage extends CommonHomePage implements IMobileUtils {

    @FindBy(xpath = "//div[@class='carousel-products']")
    protected List<ProductSlider> productSliders;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CommonCatalogPage goToCatalog() {
        tryToClickIntercepted(catalogButton);
        return initPage(getDriver(), CommonCatalogPage.class);
    }

    public CookiesPopUp getCookiesPopUp() {
        return cookiesPopUp;
    }

    @Override
    public void searchProduct(String product) {
        getHeaderMenu().searchWith(product);
    }

    @Override
    public void moveToSalesHits() {
        salesHitsTitle.scrollTo();
    }

    @Override
    public Set<String> getSalesHitsNames() {
        return productSliders.get(0).getProductNamesFromSlider();
    }

}
