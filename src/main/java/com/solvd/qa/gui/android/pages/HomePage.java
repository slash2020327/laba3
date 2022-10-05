package com.solvd.qa.gui.android.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.android.components.ProductSlider;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import com.solvd.qa.gui.common.pages.CommonHomePage;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CommonHomePage.class)
public class HomePage extends CommonHomePage implements IMobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

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
        while (!salesHitsTitle.isChecked()) {
            try {
                swipeUp(1);
                salesHitsTitle.check();
            } catch (ElementClickInterceptedException e) {
                LOGGER.warn("Element click intercepted trying to check again");
                JavaScriptUtils.hideHeader(getDriver());
                salesHitsTitle.check();
                JavaScriptUtils.hidePreviews(getDriver(), previews.size());
                break;
            }
        }
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
