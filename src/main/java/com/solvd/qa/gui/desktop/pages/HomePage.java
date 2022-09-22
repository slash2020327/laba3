package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.desktop.components.CookiesPopUp;
import com.solvd.qa.gui.desktop.components.ProductSlider;
import com.solvd.qa.utils.JavaScriptUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;


public class HomePage extends PageWithProductCards implements IMobileUtils {

    @FindBy(xpath = "//a[@href='/catalog/']")
    protected ExtendedWebElement catalogButton;

    @FindBy(xpath = "//div[@class='modal-popup']")
    protected CookiesPopUp cookiesPopUp;

    @FindBy(xpath = "//div[text()='Хиты продаж']")
    protected ExtendedWebElement salesHitsTitle;

    @FindBy(xpath = "//div[@class='carousel-products']")
    protected List<ProductSlider> productSliders;

    public CatalogPage goToCatalog() {
        tryToClickIntercepted(catalogButton);
        return new CatalogPage(getDriver());
    }

    public CookiesPopUp getCookiesPopUp() {
        return cookiesPopUp;
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void moveToSalesHits() {
        //Desktop
        salesHitsTitle.scrollTo();

        //Android
//        while (!salesHitsTitle.isChecked()) {
//            try {
//                swipeUp(1);
//                salesHitsTitle.check();
//            } catch (ElementClickInterceptedException e) {
//                JavaScriptUtils.hideHeader(getDriver());
//                salesHitsTitle.check();
//                JavascriptExecutor js = (JavascriptExecutor) getDriver();
//                for (int i = 0; i < 50; i++) {
//                    js.executeScript(String.format("return document.getElementsByClassName('c-preview')[%s].style.visibility = 'hidden';", i));
//                }
//                break;
//            }
//        }
    }

    public Set<String> getSalesHitsNames(){
        return productSliders.get(0).getProductNamesFromSlider();
    }

}
