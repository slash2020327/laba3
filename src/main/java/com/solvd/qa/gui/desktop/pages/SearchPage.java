package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CommonSearchPage.class)
public class SearchPage extends CommonSearchPage {

    @FindBy(xpath = "//div[@class='section-heading__title']")
    private ExtendedWebElement searchResultTitle;

    @FindBy(xpath = "//div[text()='%s ' or text()='%s']/parent::label[@class='inp-box__label']")
    private ExtendedWebElement checkBox;

    @FindBy(xpath = "(//div[@class='filter-line']/button) | (//a[@aria-label='change-view']) ")
    private ExtendedWebElement changeViewButton;

    public SearchPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(searchResultTitle);
    }

    public void clickCheckBoxByName(String name) {
        checkBox.format(name, name).click();
    }

    @Override
    public void openComparePage() {
        getHeaderMenu().openCompareDropDown();
        getHeaderMenu().getCompareDropDown().openComparePage();
    }

    @Override
    public void addProductsToCompare(List<String> products) {
        for (String productName : products) {
            getProductByName(productName).addToCompare();
        }
    }

    @Override
    public void openCart() {
        getHeaderMenu().openCart();
    }

    @Override
    public void chooseCategoryByName(String name) {
        clickCheckBoxByName(name);
    }

    @Override
    public void addProductsToCardByProductCodes(List<String> productCodes) {
        for (String productCode : productCodes) {
            getProductByCode(Integer.parseInt(productCode)).addToCart();
            getCartPopUp().backToShopping();
        }
    }
}
