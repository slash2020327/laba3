package com.solvd.qa.dataprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileReader.class);
    private Properties properties;
    private static final String PROPERTY_FILE_PATH = "src/main/resources/config.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                LOGGER.error("I/O exception has occurred");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
            throw new RuntimeException("Configuration.properties not found at " + PROPERTY_FILE_PATH);
        }
    }

    public String getValueByKey(String key) {
        String value = properties.getProperty(key);
        if (value != null) return value;
        else {
            LOGGER.error(String.format("Property %s is not specified in the config.properties file.", key));
            throw new RuntimeException(String.format("Property %s is not specified in the config.properties file.", key));
        }
    }

    public Map<String, String> getProperties() {
        Properties getProperties = new Properties();
        FileInputStream inputStream = null;
        HashMap<String, String> propertyMap = new HashMap<String, String>();
        try {
            inputStream = new FileInputStream(PROPERTY_FILE_PATH);
            getProperties.load(inputStream);
            propertyMap.putAll((Map) getProperties);

        } catch (Exception e) {
            LOGGER.error("Could not load properties from " + PROPERTY_FILE_PATH, e);
        }
        return propertyMap;
    }

}
