package com.solvd.qa.pages;

import com.solvd.qa.components.CookiesPopUp;
import com.solvd.qa.components.HeaderMenu;
import com.solvd.qa.components.SupportChat;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    private static final int MAX_RETRY_ATTEMPTS = 10;

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
        WaitUtils.pause(1);
        productCards = getProductCards();
        for (WebElement card : productCards) {
            if (card.getText().contains(name)) {
                return card;
            }
        }
        return null;
    }

    private void clickOnProductCardCompareButton(WebElement productCard) {
        WaitUtils.pause(1);
        productCard.findElement(compareButtonLocator).click();
    }

    public void clickOnProductCardCompareButtonByName(String name) {
        int attempts = MAX_RETRY_ATTEMPTS;
        while (attempts >= 0) {
            if (attempts == 0) {
                LOGGER.error(String.format("No compareButton was found after %s attempts", MAX_RETRY_ATTEMPTS));
                throw new NoSuchElementException("No compareButton was found");
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

    public void openPage() {
        driver.get(expectedPageUrl);
    }

    public boolean isPageOpened() {
        return driver.getCurrentUrl().split("\\?")[0].equals(expectedPageUrl);
    }

    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(driver);
    }

    public CookiesPopUp getCookiePopUp() {
        return new CookiesPopUp(driver);
    }

    public SupportChat getSupportChatDiv() {
        return new SupportChat(driver);
    }

    public void clickAddToCartButtonByName(String name) {
        WaitUtils.pause(1);
        getProductCardByName(name).findElement(addToCartButtonLocator).click();
    }


}

