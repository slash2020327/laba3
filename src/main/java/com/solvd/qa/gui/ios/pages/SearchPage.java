package com.solvd.qa.gui.ios.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.factory.ICustomTypePageFactory;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonSearchPage;
import com.solvd.qa.gui.ios.components.ProductCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.solvd.qa.utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;


@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CommonSearchPage.class)
public class SearchPage extends CommonSearchPage implements ICustomTypePageFactory, IMobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchPage.class);

    @FindBy(xpath = ".//div[@data-product_id]")
    protected List<ProductCard> productCards;

    @FindBy(xpath = "//div[@class='modal-popup modal-product-cart']/button[@aria-label='close modal']")
    private ExtendedWebElement closeCartPopUp;

    @FindBy(xpath = "//div[@class='g-navbar']/a[@href='/cart/']")
    private ExtendedWebElement openCartButton;

    @FindBy(xpath = "//h1[@class='heading']")
    private ExtendedWebElement searchTitle;

    @FindBy(xpath = "//span[text()='Сортировка и фильтры']/ancestor::button")
    private ExtendedWebElement sortAndFilterButton;

    @FindBy(xpath = "//div[text()='%s ']/ancestor::div[@class='inp-box']/label")
    private ExtendedWebElement categoryCheckBox;

    @FindBy(xpath = "//span[text()='Посмотреть']/ancestor::button")
    private ExtendedWebElement viewFilteredProductsButton;

    @FindBy(xpath = "//a[contains(@href, '/compare')]")
    private ExtendedWebElement goToCompareButton;

    public SearchPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(searchTitle);
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    private void clickViewFilteredProductsButton() {
        viewFilteredProductsButton.click();
    }

    private void checkCategoryCheckBoxByName(String name) {
        categoryCheckBox.format(name).click();
    }

    private void clickSortAndFilterButton() {
        sortAndFilterButton.click();
    }

    private void clickGoToCompareButton() {
        goToCompareButton.click();
    }

    @Override
    public void openCart() {
        waitUntil(ExpectedConditions.visibilityOf(openCartButton.getElement()), MAXIMAL_TIMEOUT);
        openCartButton.click();
    }

    @Override
    public void openComparePage() {
        clickGoToCompareButton();
    }

    @Override
    public void chooseCategoryByName(String name) {
        clickSortAndFilterButton();
        checkCategoryCheckBoxByName(name);
        clickViewFilteredProductsButton();
    }


    @Override
    public void addProductsToCompare(List<String> products) {
        WaitUtils.waitForProductCardToLoad(productCards);
        for (ProductCard product : productCards) {
            if (products.contains(product.getProductName())) {
                product.addToCompare();
            }
        }
    }

    @Override
    public void addProductsToCardByProductCodes(List<String> productCodes) {
        WaitUtils.waitForProductCardToLoad(productCards);
        for (ProductCard card : productCards) {
            if (productCodes.contains(card.getProductCode())) {
                card.addToCart();
                waitUntil(ExpectedConditions.visibilityOf(closeCartPopUp.getElement()), MAXIMAL_TIMEOUT);
                closeCartPopUp.click();
            }
        }
    }
}