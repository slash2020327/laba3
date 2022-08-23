package com.solvd.qa.dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath="src/main/resources/config.properties";
    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }
    public String getURL(){
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("driverPath not specified in the config.properties file.");
    }
    public String getDriverPath(){
        String driverPath = properties.getProperty("driverPath");
        if(driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the config.properties file.");
    }
    public String getRemoteUrl(){
        String remoteUrl = properties.getProperty("remoteUrl");
        if(remoteUrl != null) return remoteUrl;
        else throw new RuntimeException("driverPath not specified in the config.properties file.");
    }
}
