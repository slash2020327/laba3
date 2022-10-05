package com.solvd.qa.gui.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.components.BaseComponent;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderMenu extends BaseComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderMenu.class);

    @FindBy(xpath = "//*[@placeholder='Поиск товара']")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//div[text()='Корзина']/ancestor::div[@class='h-added-drop h-drop']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//input[@type='text' and @placeholder='Поиск товара' and @maxlength='90' ]")
    private ExtendedWebElement hiddenSearchField;

    @FindBy(xpath = "//div[@class='multi-search-header']")
    private ExtendedWebElement searchFieldTest;

    @FindBy(xpath = "//div[text()='Сравнение']/ancestor::div[@class='h-added-drop h-drop js-drop-select']")
    private ExtendedWebElement compareButton;

    @FindBy(xpath = "//div[@class='h-drop__content']")
    private CompareDropDown compareDropDown;

    @FindBy(xpath = " //button[@class='multi-search-header__submit']")
    private ExtendedWebElement searchButton;

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CompareDropDown getCompareDropDown() {
        return compareDropDown;
    }

    public void openCompareDropDown() {
        compareButton.click();
    }

    public void searchWith(String search) {
        searchField.click();
        hiddenSearchField.type(search);
        searchButton.click();
    }

    public void openCart() {
        cartButton.click();
    }
}
