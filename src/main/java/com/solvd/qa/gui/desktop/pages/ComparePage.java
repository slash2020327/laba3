package com.solvd.qa.gui.desktop.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonComparePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CommonComparePage.class)
public class ComparePage extends CommonComparePage {

    @FindBy(xpath = "//div[@class='section-heading__title']")
    private ExtendedWebElement comparisonTitle;

    @FindBy(xpath = "(//div[contains(text(), 'Только различия')])[1]")
    private ExtendedWebElement onlyDifferencesRadioButton;

    @FindBy(xpath = "(//div[contains(text(), 'Только одинаковые')])[1]")
    private ExtendedWebElement onlySimilarRadioButton;

    @FindBy(xpath = "(//div[contains(text(), 'Все характеристики')])[1]")
    private ExtendedWebElement allCharacteristicsRadioButton;

    @FindBy(xpath = "(//div[contains(text(), 'Акции')])[1]")
    private ExtendedWebElement salesRadioButton;

    @FindBy(xpath = "//div[contains(text(), 'Бренд')]/ancestor::div[@class='compare-row']//span[@class='compare-cell']")
    private List<ExtendedWebElement> brandsRow;

    public ComparePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(comparisonTitle);
    }

    @Override
    public List<String> getBrandsRowBrandNames() {
        List<String> brandsRowBrandNames = new ArrayList<>();
        for (ExtendedWebElement extendedWebElement : brandsRow) {
            brandsRowBrandNames.add(extendedWebElement.getText().toLowerCase());
        }
        return brandsRowBrandNames;
    }
}
