package com.solvd.qa.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductSlider extends BaseComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSlider.class);

    @FindBy(xpath = ".//div[@class='carousel-pagination swiper-pagination-clickable swiper-pagination-bullets']//span[contains(@class, 'swiper-pagination-bullet')]")
    private List<ExtendedWebElement> sliderPages;

    @FindBy(xpath = "./div[@class='swiper-button-next ic-chevron-down']")
    private ExtendedWebElement sliderButton;

    @FindBy(xpath = ".//div[@data-product_id]")
    private List<ProductCard> productCards;

    public ProductSlider(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    private ProductCard getProductCardByName(String name) {
        productCards = getProductCards();
        for (ProductCard card : productCards) {
            if (card.getProductName().equals(name)) {
                return card;
            }
        }
        throw new RuntimeException("Unable to find product: " + name);
    }

    public void clickSliderButton() {
        tryToClickIntercepted(sliderButton);
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public ProductCard getProductByName(String name) {
        int currentPageNumber = 1;
        while (currentPageNumber <= sliderPages.size()) {
            try {
                return getProductCardByName(name);
            } catch (Exception e) {
                currentPageNumber++;
                clickSliderButton();
            }
        }
        throw new RuntimeException(name + " was not found");
    }
}
