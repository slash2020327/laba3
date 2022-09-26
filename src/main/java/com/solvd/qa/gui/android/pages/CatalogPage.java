package com.solvd.qa.gui.android.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CommonCatalogPage.class)
public class CatalogPage extends CommonCatalogPage implements IMobileUtils {

    @FindBy(xpath = "//div[@class='m-item']")
    private List<ExtendedWebElement> categoryList;

    @FindBy(xpath = "//div[@class='s-item']/a[@href]")
    private List<ExtendedWebElement> subCategoriesList;

    public CatalogPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get("url") + "/catalog/");
    }

    private void clickCategoryByName(String name) {
        waitUntil(ExpectedConditions.elementToBeClickable(categoryList.get(0).getElement()), 5);
        for (ExtendedWebElement category : categoryList) {
            if (category.getText().contains(name)) {
                try {
                    category.click();
                } catch (Exception e) {
                    swipeUp(1);
                    category.click();
                }
            }
        }
    }

    @Override
    public String getCategoryLinkByName(String name) {
        clickCategoryByName(name);
        for (ExtendedWebElement category : subCategoriesList) {
            if (category.getText().equals(name)) {
                return R.CONFIG.get("url") + category.getAttribute("href");
            }
        }
        throw new RuntimeException("Category with name " + name + " was not found");
    }

    @Override
    public void clickOnCategoryByName(String name) {
        for (ExtendedWebElement category : subCategoriesList) {
            if (category.getText().equals(name)) {
                category.click();
                break;
            }
        }
    }
}
