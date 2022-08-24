package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitUtils.class);

    public static void pause(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOGGER.error("Unable to pause");
            throw new RuntimeException("Unable to pause", e);
        }
    }
}
