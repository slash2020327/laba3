package com.solvd.qa.gui.android.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.pages.CommonCatalogPage;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CommonCatalogPage.class)
public class CatalogPage extends CommonCatalogPage implements IMobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogPage.class);

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
        try {
            category.format(name).click();
        } catch (ElementClickInterceptedException e) {
            LOGGER.warn("Element click intercepted, trying to click again with swipe");
            swipeUp(1);
            category.format(name).click();
        }
    }

    @Override
    public String getCategoryLinkByName(String name) {
        clickCategoryByName(name);
        return R.CONFIG.get("url") + subCategory.format(name).getAttribute("href");
    }

    @Override
    public void clickOnCategoryByName(String name) {
        subCategory.format(name).click();
        waitUntil(ExpectedConditions.visibilityOf(title.getElement()), MAXIMAL_TIMEOUT);
    }
}
