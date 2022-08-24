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
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class AbstractTest {

    RemoteWebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeMethod
    public void setup() {

        System.setProperty(ConfigFileReader.getValueByKey("webdriver.name"), ConfigFileReader.getValueByKey("driverPath"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments(ConfigFileReader.getValueByKey("windowSize"));
        options.setCapability("browserVersion", ConfigFileReader.getValueByKey("browserVersion"));
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", ConfigFileReader.getValueByKey("selenoidOptions.name"));
            /* How to set session timeout */
            put("sessionTimeout", ConfigFileReader.getValueByKey("selenoidOptions.sessionTimeout"));
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add(ConfigFileReader.getValueByKey("selenoidOptions.timezone"));
            }});
            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            /* How to enable video recording */
            put("enableVideo", Boolean.valueOf(ConfigFileReader.getValueByKey("selenoidOptions.enableVideo")));
            put("enableVNC", Boolean.valueOf(ConfigFileReader.getValueByKey("selenoidOptions.enableVNC")));
        }});
        try {
            driver = new RemoteWebDriver(new URL(ConfigFileReader.getValueByKey("remoteUrl")), options);
        } catch (MalformedURLException e) {
            LOGGER.error("Could not access remoteWebDriver by URL " + ConfigFileReader.getValueByKey("remoteUrl"));
            throw new RuntimeException("Could not access remoteWebDriver by URL", e);
        }
        driver = new ChromeDriver(options);
        driver.get(ConfigFileReader.getValueByKey("url"));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
