package com.solvd.qa;

import com.solvd.qa.pages.CatalogPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class App
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "/Users/sbedulin/Downloads/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920,1080");
        options.addArguments("--incognito");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://5element.by/catalog");
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.getCookiePopUp().clickAcceptCookieButton();
        String categoryName = "Телевизоры";
        String checkLink = catalogPage.getCategoryLinkByName(categoryName);
        catalogPage.clickOnCategoryByName(categoryName);
        Assert.assertEquals(driver.getCurrentUrl(), checkLink);
        System.out.println(driver.getCurrentUrl());
        System.out.println(checkLink);
        System.out.println(driver.getTitle());
        String expectedURL = "https://5element.by/catalog/";
        System.out.println(driver.getCurrentUrl().contains(expectedURL));
        //WebElement smart = driver.findElement(By.xpath("(//a[@href='/catalog/377-smartfony'])[2]"));
        //String url = smart.getAttribute("href");
       // smart.click();
//        System.out.println(driver.getCurrentUrl());
//        System.out.println(url);
//        Assert.assertEquals(driver.getCurrentUrl(), url);











        //searchPage.clickOnProductCardCompareButton(searchPage.getProductCardByName("Xiaomi Redmi 9C 4GB/128GB Lavender Purple EU без NFC"));
        //searchPage.clickOnProductCardCompareButton(searchPage.getProductCardByName("Xiaomi 11 Lite 5G NE 6GB/128GB (черный) EU"));

        //XPath
        //Catalog Smartphone XPath //div[@class='catalog-navigation-category']//a[@href='/catalog/377-smartfony']
        // //*[@name="login"]
        // //*[@class="multi-search-header"]
        // //div[@class="h-search"]
        // //*[@placeholder="Поиск товара"]
        // //a[contains(text(), 'iPhone XR')]/following::a[@title='В сравнение']
        // //input[@type='checkbox']/following::div[contains(text(), "Смартфоны")]
        // //*[@id="app"]/main/div/div[2]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[2]/div/div[5]/a[2]/span
        // //*[@id="app"]/main/div/div[2]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[3]/div/div[5]/a[2]/span

        // //div[@data-product_id]/div[@class='c-nav']/a[@aria-label='compare']
        // /div[@class='c-nav']/a[@aria-label='compare']

    }

}
