package com.solvd.qa.utils;

import com.solvd.qa.gui.android.components.ProductCard;
import com.solvd.qa.gui.common.components.CommonProductCard;
import com.solvd.qa.gui.ios.pages.SearchPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.qaprosoft.carina.core.foundation.utils.common.CommonUtils.pause;
import static com.solvd.qa.constants.WaitTime.MINIMAL_TIMEOUT;

public class WaitUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitUtils.class);

    public static void waitForProductCardToLoad(List<? extends CommonProductCard> productCards) {
        try {
            productCards.forEach(CommonProductCard::getProductName);
        } catch (StaleElementReferenceException e) {
            LOGGER.warn("ProductCards are stale waiting for update");
            pause(MINIMAL_TIMEOUT);
        }
    }
}
