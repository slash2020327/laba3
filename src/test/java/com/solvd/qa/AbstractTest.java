package com.solvd.qa;

import com.solvd.qa.dataprovider.ConfigFileReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AbstractTest {
    RemoteWebDriver driver;

    @BeforeMethod
    public void setup() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty(configFileReader.getValueByKey("webdriver.name"), configFileReader.getValueByKey("driverPath"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments(configFileReader.getValueByKey("windowSize"));
        options.setCapability("browserVersion", configFileReader.getValueByKey("browserVersion"));
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", configFileReader.getValueByKey("selenoidOptions.name"));
            /* How to set session timeout */
            put("sessionTimeout", configFileReader.getValueByKey("selenoidOptions.sessionTimeout"));
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add(configFileReader.getValueByKey("selenoidOptions.timezone"));
            }});
            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            /* How to enable video recording */
            put("enableVideo", Boolean.valueOf(configFileReader.getValueByKey("selenoidOptions.enableVideo")));
            put("enableVNC", Boolean.valueOf(configFileReader.getValueByKey("selenoidOptions.enableVNC")));
        }});
        try {
            driver = new RemoteWebDriver(new URL(configFileReader.getValueByKey("remoteUrl")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        //driver = new ChromeDriver(options);
        driver.get(configFileReader.getValueByKey("url"));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
