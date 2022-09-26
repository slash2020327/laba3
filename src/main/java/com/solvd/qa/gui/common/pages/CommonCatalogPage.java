package com.solvd.qa.gui.common.pages;

import com.solvd.qa.gui.desktop.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class CommonCatalogPage extends BasePage {

    public CommonCatalogPage(WebDriver driver) {
        super(driver);
    }

    public abstract String getCategoryLinkByName(String name);

    public abstract void clickOnCategoryByName(String name);

}
