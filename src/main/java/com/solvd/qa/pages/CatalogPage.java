package com.solvd.qa.pages;

import com.solvd.qa.dataprovider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogPage extends AbstractPage {

    @FindBy(xpath = "//main[@class='g-main']//li//a")
    private List<WebElement> categories;

    public CatalogPage(WebDriver driver) {
        super(driver);
        expectedPageUrl = configFileReader.getValueByKey("url") + "/catalog/";
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> getCategories() {
        return categories;
    }

    private WebElement getCategoryByName(String name) {
        for (WebElement category : categories) {
            if (category.getText().equals(name)) {
                return category;
            }
        }
        return null;
    }

    private String getCategoryLink(WebElement category) {
        return category.getAttribute("href");
    }

    public String getCategoryLinkByName(String name) {
        return getCategoryLink(getCategoryByName(name));
    }

    public void clickOnCategoryByName(String name) {
        getCategoryByName(name).click();
    }

}
