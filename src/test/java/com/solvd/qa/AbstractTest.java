package com.solvd.qa;

import com.solvd.qa.dataprovider.ConfigFileReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class AbstractTest {

    RemoteWebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeMethod
    public void setup() {

        ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty(configFileReader.getProperties().get("webdriver.name"), configFileReader.getProperties().get("driverPath"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments(configFileReader.getProperties().get("windowSize"));
        options.setCapability("browserVersion", configFileReader.getProperties().get("browserVersion"));
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", configFileReader.getProperties().get("selenoidOptions.name"));
            /* How to set session timeout */
            put("sessionTimeout", configFileReader.getProperties().get("selenoidOptions.sessionTimeout"));
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add(configFileReader.getProperties().get("selenoidOptions.timezone"));
            }});
            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            /* How to enable video recording */
            put("enableVideo", Boolean.valueOf(configFileReader.getProperties().get("selenoidOptions.enableVideo")));
            put("enableVNC", Boolean.valueOf(configFileReader.getProperties().get("selenoidOptions.enableVNC")));
        }});
        try {
            driver = new RemoteWebDriver(new URL(configFileReader.getProperties().get("remoteUrl")), options);
        } catch (MalformedURLException e) {
            LOGGER.error("Could not access remoteWebDriver by URL " + configFileReader.getProperties().get("remoteUrl"));
            throw new RuntimeException("Could not access remoteWebDriver by URL", e);
        }
        driver = new ChromeDriver(options);
        driver.get(configFileReader.getProperties().get("url"));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
