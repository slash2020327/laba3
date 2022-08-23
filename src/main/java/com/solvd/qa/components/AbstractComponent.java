package com.solvd.qa.components;

import org.openqa.selenium.WebDriver;

public class AbstractComponent {

    protected WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;

    }
}
