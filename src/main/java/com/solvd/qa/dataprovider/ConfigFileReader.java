package com.solvd.qa.dataprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileReader.class);

    private static Properties properties;

    private static final String PROPERTY_FILE_PATH = "src/main/resources/config.properties";

    public static void initConfig() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                LOGGER.error("I/O exception has occurred");
                throw new RuntimeException("I/O exception has occurred");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
            throw new RuntimeException("Configuration.properties not found at " + PROPERTY_FILE_PATH);
        }
    }

    public static String getValueByKey(String key) {
        if (properties == null) {
            initConfig();
        }
        String value = properties.getProperty(key);
        if (value != null) return value;
        else {
            LOGGER.error(String.format("Property %s is not specified in the config.properties file.", key));
            throw new RuntimeException(String.format("Property %s is not specified in the config.properties file.", key));
        }
    }


}
