package com.solvd.qa.gui.desktop.components;

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

    @FindBy(xpath = ".//div[contains(@class, 'carousel-pagination')]//span[contains(@class, 'swiper-pagination-bullet')]")
    protected List<ExtendedWebElement> sliderPages;

    @FindBy(xpath = ".//div[@data-product_id]")
    protected List<ProductCard> productCards;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSlider.class);

    @FindBy(xpath = "./div[@class='swiper-button-next ic-chevron-down']")
    private ExtendedWebElement sliderButton;

    public ProductSlider(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void moveSlider() {
        tryToClickIntercepted(sliderButton);
    }

    public Set<String> getProductNamesFromSlider() {
        Set<String> productNames = new HashSet<String>();
        int currentPageNumber = 0;
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
