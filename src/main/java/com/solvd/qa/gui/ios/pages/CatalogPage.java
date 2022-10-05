package com.solvd.qa.gui.ios.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import com.solvd.qa.utils.IScrollUtilsIOS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CommonCatalogPage.class)
public class CatalogPage extends CommonCatalogPage implements IScrollUtilsIOS {

    @FindBy(xpath = "//div[@class='item-name' and contains(text(), '%s')]/parent::a")
    private ExtendedWebElement category;

    @FindBy(xpath = "//div[@class='s-item']/a[@href and text()='%s']")
    private ExtendedWebElement subCategory;

    @FindBy(xpath = "//h1[@class='heading ec-section-name']")
    private ExtendedWebElement title;

    public CatalogPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get("url") + "/catalog/");
    }

    private void clickCategoryByName(String name) {
        category.format(name).click();
    }

    @Override
    public String getCategoryLinkByName(String name) {
        clickCategoryByName(name);
        return subCategory.format(name).getAttribute("href");
    }

    @Override
    public void clickOnCategoryByName(String name) {
        subCategory.format(name).click();
        waitUntil(ExpectedConditions.visibilityOf(title.getElement()), MAXIMAL_TIMEOUT);
    }
}
