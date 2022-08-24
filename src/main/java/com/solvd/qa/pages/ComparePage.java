package com.solvd.qa.pages;

import com.solvd.qa.dataprovider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class ComparePage extends AbstractPage {

    @FindBy(xpath = "(//div[contains(text(), 'Только различия')])[1]")
    private WebElement onlyDifferencesRadioButton;

    @FindBy(xpath = "(//div[contains(text(), 'Только одинаковые')])[1]")
    private WebElement onlySimilarRadioButton;

    @FindBy(xpath = "(//div[contains(text(), 'Все характеристики')])[1]")
    private WebElement allCharacteristicsRadioButton;

    @FindBy(xpath = "(//div[contains(text(), 'Акции')])[1]")
    private WebElement salesRadioButton;

    @FindBy(xpath = "//div[contains(text(), 'Бренд')]/ancestor::div[@class='compare-row']//span[@class='compare-cell']")
    private List<WebElement> brandsRow;

    public ComparePage(WebDriver driver) {
        super(driver);
        expectedPageUrl = configFileReader.getValueByKey("url") + "/compare/";
        PageFactory.initElements(driver, this);
    }

    public void clickOnOnlyDifferencesRadioButton() {
        WaitUtils.pause(1);
        onlyDifferencesRadioButton.click();
    }

    public void clickOnOnlySimilarRadioButton() {
        WaitUtils.pause(1);
        onlySimilarRadioButton.click();
    }

    public void clickOnAllCharacteristicsRadioButton() {
        WaitUtils.pause(1);
        allCharacteristicsRadioButton.click();
    }

    public void clickOnSalesRadioButton() {
        WaitUtils.pause(1);
        salesRadioButton.click();
    }

    public List<String> getBrandsRowBrandNames() {
        List<String> brandsRowBrandNames = new ArrayList<>();
        for (int i = 0; i < brandsRow.size(); i++) {
            brandsRowBrandNames.add(brandsRow.get(i).getText().toLowerCase());

        }
        return brandsRowBrandNames;
    }

    @Override
    public boolean isPageOpened() {
        return driver.getCurrentUrl().split("\\%")[0].equals(expectedPageUrl);
    }


}
