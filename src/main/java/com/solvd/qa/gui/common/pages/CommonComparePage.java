package com.solvd.qa.gui.common.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CommonComparePage extends BasePage {

    public CommonComparePage(WebDriver driver) {
        super(driver);
    }

    public abstract List<String> getBrandsRowBrandNames();
}
