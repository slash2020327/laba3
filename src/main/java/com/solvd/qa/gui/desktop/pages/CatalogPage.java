package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CommonCatalogPage.class)
public class CatalogPage extends CommonCatalogPage {

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

    @Override
    public String getCategoryLinkByName(String name) {
        return getCategoryLink(getCategoryByName(name));
    }

    @Override
    public void clickOnCategoryByName(String name) {
        getCategoryByName(name).click();
    }
}
