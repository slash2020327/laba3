package com.solvd.qa.dataprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileReader.class);
    private Properties properties;
    private static final String PROPERTY_FILE_PATH ="src/main/resources/config.properties";
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

    public String getValueByKey(String key){
        String value = properties.getProperty(key);
        if(value != null) return value;
        else throw new RuntimeException("Such property is not specified in the config.properties file.");
    }

}
