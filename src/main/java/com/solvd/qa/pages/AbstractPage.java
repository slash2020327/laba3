package com.solvd.qa.pages;

import com.solvd.qa.components.CookiesPopUp;
import com.solvd.qa.components.HeaderMenu;
import com.solvd.qa.components.SupportChatDiv;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractPage {

    private By productCardsLocator = By.xpath("//div[@data-product_id]");
    private By compareButtonLocator = By.xpath(".//div[@class='c-nav']/a[@aria-label='compare']");
    private By addToCartButtonLocator = By.xpath(".//button[@data-product_id and @data-product_name]");

    protected WebDriver driver;
    protected List<WebElement> productCards;
    protected String expectedPageUrl;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> getProductCards() {
        productCards = driver.findElements(productCardsLocator);
        return productCards;
    }

    private WebElement getProductCardByName(String name) {
        WaitUtils.pause(500);
        productCards = getProductCards();
        for (WebElement card : productCards) {
            if (card.getText().contains(name)) {
                return card;
            }
        }
        return null;
    }

    private void clickOnProductCardCompareButton(WebElement productCard) {
        WaitUtils.pause(500);
        productCard.findElement(compareButtonLocator).click();
    }

    public void clickOnProductCardCompareButtonByName(String name) {
        int attempts = 10;
        while (attempts >= 0) {
            if (attempts == 0) {
                throw new NoSuchElementException();
            }
            try {
                clickOnProductCardCompareButton(getProductCardByName(name));
                break;
            } catch (ElementClickInterceptedException e) {
                Actions action = new Actions(driver);
                action.sendKeys(Keys.PAGE_UP).build().perform();
                attempts--;
            }
        }
    }

    public boolean isPageOpened() {
        //return expectedPageUrl.equals(driver.getCurrentUrl().split("\\?")[0]);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='section section--first' or @class='section section--first section--last']")));
        return driver.getCurrentUrl().contains(expectedPageUrl);
    }

    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(driver);
    }

    public CookiesPopUp getCookiePopUp() {
        return new CookiesPopUp(driver);
    }

    public SupportChatDiv getSupportChatDiv() {
        return new SupportChatDiv(driver);
    }


    public void clickAddToCartButtonByName(String name) {
        WaitUtils.pause(1000);
        getProductCardByName(name).findElement(addToCartButtonLocator).click();
    }


}

