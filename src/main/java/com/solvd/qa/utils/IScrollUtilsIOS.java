package com.solvd.qa.utils;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.IDriverPool;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public interface IScrollUtilsIOS extends IDriverPool {

    Logger LOGGER = LoggerFactory.getLogger(IScrollUtilsIOS.class);

    default void mobileScrollScreenIOS(IMobileUtils.Direction direction) {
        LOGGER.info("mobileScrollScreenIOS(): direction: '" + direction + "'");

        // Animation default time:
        //  - iOS: 200 ms
        final int ANIMATION_TIME = 200; // ms
        final HashMap<String, String> scrollObject = new HashMap<String, String>();

        switch (direction) {
            case DOWN: // from down to up (! differs from mobile:swipe)
                scrollObject.put("direction", "down");
                break;
            case UP: // from up to down (! differs from mobile:swipe)
                scrollObject.put("direction", "up");
                break;
            case LEFT: // from left to right (! differs from mobile:swipe)
                scrollObject.put("direction", "left");
                break;
            case RIGHT: // from right to left (! differs from mobile:swipe)
                scrollObject.put("direction", "right");
                break;
            default:
                throw new IllegalArgumentException("mobileScrollIOS(): dir: '" + direction + "' NOT supported");
        }
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("mobile:scroll", scrollObject); // swipe faster then scroll
            Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
        } catch (Exception e) {
            LOGGER.error("mobileScrollIOS(): FAILED\n" + e.getMessage());
        }
    }

    default void mobileSwipeScreenIOS(IMobileUtils.Direction direction) {
        LOGGER.info("mobileSwipeScreenIOS(): direction: '" + direction + "'");

        // Animation default time:
        //  - iOS: 200 ms
        final int ANIMATION_TIME = 100; // ms
        final HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("velocity", "600");

        switch (direction) {
            case DOWN: // from up to down (! differs from mobile:scroll)
                scrollObject.put("direction", "down");
                break;
            case UP: // from down to up  (! differs from mobile:scroll)
                scrollObject.put("direction", "up");
                break;
            case LEFT: // from right to left  (! differs from mobile:scroll)
                scrollObject.put("direction", "left");
                break;
            case RIGHT: // from left to right  (! differs from mobile:scroll)
                scrollObject.put("direction", "right");
                break;
            default:
                throw new IllegalArgumentException("mobileSwipeScreenIOS(): direction: '" + direction + "' NOT supported");
        }
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("mobile:swipe", scrollObject);
            Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
        } catch (Exception e) {
            LOGGER.error("mobileSwipeScreenIOS(): FAILED\n" + e.getMessage());
        }
    }
}
