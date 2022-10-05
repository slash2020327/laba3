package com.solvd.qa.gui.ios.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import com.solvd.qa.gui.ios.components.ProductSlider;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CommonHomePage.class)
public class HomePage extends CommonHomePage implements IMobileUtils {

    @FindBy(xpath = "//div[@class='carousel-products']")
    protected List<ProductSlider> productSliders;

    @FindBy(xpath = "//input[@placeholder='Поиск товара']")
    private ExtendedWebElement searchBar;

    @FindBy(xpath = "//button[@class='s-btn-submit btn btn--clear']")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = "//*[@class='c-preview']")
    private List<ExtendedWebElement> previews;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void searchProduct(String product) {
        searchBar.type(product);
        searchButton.click();
    }

    @Override
    public void moveToSalesHits() {
        JavaScriptUtils.scrollDown(getDriver(), 3);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        JavaScriptUtils.hidePreviews(getDriver(), previews.size());
    }

    @Override
    public CommonCatalogPage goToCatalog() {
        catalogButton.click();
        return initPage(getDriver(), CommonCatalogPage.class);
    }

    @Override
    public Set<String> getSalesHitsNames() {
        return productSliders.get(0).getProductNamesFromSlider();
    }
}
