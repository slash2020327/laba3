package com.solvd.qa.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.components.CookiesPopUp;
import com.solvd.qa.gui.components.ProductSlider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends PageWithProductCards {

    @FindBy(xpath = "//a[@href='/catalog/']")
    private ExtendedWebElement catalogButton;

    @FindBy(xpath = "//div[@class='modal-popup']")
    private CookiesPopUp cookiesPopUp;

    @FindBy(xpath = "//div[@class='carousel-products']")
    private List<ProductSlider> productSliders;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CatalogPage openCatalogPage() {
        catalogButton.click();
        return new CatalogPage(driver);
    }

    public CatalogPage goToCatalog() {
        tryToClickIntercepted(catalogButton);
        return new CatalogPage(getDriver());
    }

    public CookiesPopUp getCookiesPopUp() {
        return cookiesPopUp;
    }

    public List<ProductSlider> getProductSliders() {
        return productSliders;
    }


}
