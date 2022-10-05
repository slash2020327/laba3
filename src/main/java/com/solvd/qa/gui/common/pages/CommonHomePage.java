package com.solvd.qa.gui.common.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.desktop.components.CookiesPopUp;
import com.solvd.qa.gui.desktop.pages.PageWithProductCards;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public abstract class CommonHomePage extends PageWithProductCards {

    @FindBy(xpath = "//a[@href='/catalog/']")
    protected ExtendedWebElement catalogButton;

    @FindBy(xpath = "//div[@class='modal-popup']")
    protected CookiesPopUp cookiesPopUp;

    @FindBy(xpath = "//div[text()='Хиты продаж']")
    protected ExtendedWebElement salesHitsTitle;

    public CommonHomePage(WebDriver driver) {
        super(driver);
    }

    public abstract void searchProduct(String product);

    public abstract void moveToSalesHits();

    public abstract CommonCatalogPage goToCatalog();

    public abstract Set<String> getSalesHitsNames();
}
