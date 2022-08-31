package com.solvd.qa.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageWithProductCards {

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

}
