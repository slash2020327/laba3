package com.solvd.qa.pages;

import com.solvd.qa.components.HeaderMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    public HomePage(WebDriver driver) {
        super(driver);
        expectedPageUrl = "https://5element.by/";
        PageFactory.initElements(driver, this);
    }

    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(driver);
    }
}
