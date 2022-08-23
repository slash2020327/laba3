package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitUtils.class);
    public static void pause(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            LOGGER.error("Thread is not available");
            throw new RuntimeException(e);
        }
    }
}
