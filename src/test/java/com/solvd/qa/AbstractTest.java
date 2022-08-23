package com.solvd.qa;

import com.solvd.qa.dataprovider.ConfigFileReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.HashMap;

public class AbstractTest {
    RemoteWebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/sbedulin/Downloads/chromedriver");
        ConfigFileReader configFileReader = new ConfigFileReader();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--window-size=1920,1080");
        options.setCapability("browserVersion", "91.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");
            /* How to set session timeout */
            put("sessionTimeout", "15m");
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            /* How to enable video recording */
            put("enableVideo", false);
            put("enableVNC", true);
        }});
        //driver = new RemoteWebDriver(new URL(configFileReader.getRemoteUrl()), options);
        driver = new ChromeDriver(options);
        driver.get(configFileReader.getURL());
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
