package com.solvd.qa.gui.android.components;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.components.BaseComponent;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductSlider extends BaseComponent implements IMobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSlider.class);

    @FindBy(xpath = ".//div[contains(@class, 'carousel-pagination')]//span[contains(@class, 'swiper-pagination-bullet')]")
    protected List<ExtendedWebElement> sliderPages;

    @FindBy(xpath = ".//div[@data-product_id]")
    protected List<ProductCard> productCards;

    public ProductSlider(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void moveSlider() {
        swipeLeft(1);
    }

    public Set<String> getProductNamesFromSlider() {
        Set<String> productNames = new HashSet<String>();
        int currentPageNumber = 1;
        while (currentPageNumber < sliderPages.size()) {
            for (ProductCard productCard : productCards) {
                if (!productCard.getProductName().isBlank()) {
                    productNames.add(productCard.getProductName());
                }
            }
            moveSlider();
            currentPageNumber++;
        }
        /*Due to loading process of all ProductCards in slider its sometimes necessary to remove blank
        element from set or wait for loading of ProductCards contents, but it will compromise method speed.
        */
        productNames.remove("");
        return productNames;
    }
}
