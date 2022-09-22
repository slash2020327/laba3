package com.solvd.qa.gui.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.desktop.pages.ComparePage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CompareDropDown extends BaseComponent {

    @FindBy(xpath = "//a[contains(text(), 'Перейти в сравнение')]")
    private ExtendedWebElement goToComparePageButton;

    public CompareDropDown(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ComparePage openComparePage() {
        goToComparePageButton.click();
        return new ComparePage(getDriver());
    }
}
