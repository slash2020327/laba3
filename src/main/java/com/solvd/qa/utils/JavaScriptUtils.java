package com.solvd.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class JavaScriptUtils {

    public static void hideSupportChat(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.getElementsByTagName('jdiv')[0].style.visibility = 'hidden';");
    }

    public static void showSupportChat(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.getElementsByTagName('jdiv')[0].style.visibility = 'visible';");
    }

    public static void hideHeader(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.getElementsByClassName('g-header')[0].style.visibility = 'hidden';");
    }

    public static void showHeader(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.getElementsByClassName('g-header')[0].style.visibility = 'visible';");
    }

    public static void scrollUp(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-250);");
    }
    public static void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250);");
    }

    public static void scrollUp(WebDriver driver, int timesToScroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i = 0; i <= timesToScroll; i++) {
            js.executeScript("window.scrollBy(0,-250);");
        }
    }
    public static void scrollDown(WebDriver driver, int timesToScroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i = 0; i <= 10; i++) {
            js.executeScript("window.scrollBy(0,250);");
        }
    }
    public static void hideNotificationPrompt(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.getElementsByClassName('sp-backdrop-info show-prompt')[0].style.visibility = 'hidden';");
    }

}
