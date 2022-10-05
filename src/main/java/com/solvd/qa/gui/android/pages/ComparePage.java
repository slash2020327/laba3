package com.solvd.qa.gui.android.pages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.solvd.qa.gui.common.pages.CommonComparePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CommonComparePage.class)
public class ComparePage extends CommonComparePage {

    @FindBy(xpath = "//div[contains(text(), 'Бренд')]/following-sibling::div//span[@class='compare-cell']")
    private List<ExtendedWebElement> brandsRow;

    @FindBy(xpath = "//h1[contains(text(), 'Сравнение товаров')]")
    private ExtendedWebElement comparisonTitle;

    public ComparePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(comparisonTitle);
    }

    @Override
    public List<String> getBrandsRowBrandNames() {
        List<String> brandsRowBrandNames = new ArrayList<>();
        for (ExtendedWebElement extendedWebElement : brandsRow) {
            if (!extendedWebElement.getText().isBlank()) {
                brandsRowBrandNames.add(extendedWebElement.getText());
            }
        }
        return brandsRowBrandNames;
    }
}
