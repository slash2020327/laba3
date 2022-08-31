package com.solvd.qa.gui.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CatalogPage extends BasePage {

    @FindBy(xpath = "//main[@class='g-main']//li//a")
    private List<ExtendedWebElement> categories;

    public CatalogPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get("url") + "/catalog/");
    }

    private ExtendedWebElement getCategoryByName(String name) {
        for (ExtendedWebElement category : categories) {
            if (category.getText().equals(name)) {
                return category;
            }
        }
        throw new RuntimeException("Category with name " + name + " was not found");
    }

    private String getCategoryLink(ExtendedWebElement category) {
        return category.getAttribute("href");
    }

    public String getCategoryLinkByName(String name) {
        return getCategoryLink(getCategoryByName(name));
    }

    public void clickOnCategoryByName(String name) {
        getCategoryByName(name).click();
    }
}
