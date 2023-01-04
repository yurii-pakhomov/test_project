package com.ipakhomov.utils;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {
    private final String propertyFile = "test.properties";
    private final String appUrlProperty = "app.url";
    private final String webDriverModeProperty = "web.driver.mode";
    private Properties properties;

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(propertyFile).getFile());
            reader = new BufferedReader(new FileReader(file));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s file is not found", propertyFile));
        }
    }

    public WebDriverMode getWebDriverMode() {
        String webDriverMode = properties.getProperty(webDriverModeProperty);
        if (webDriverMode == null) {
            throw new IllegalArgumentException(String.format("%s property is not specified in property file", webDriverModeProperty));
        }
        return WebDriverMode.fromString(webDriverMode);
    }

    public String getApplicationUrl() {
        String applicationUrl = properties.getProperty(appUrlProperty);
        if (applicationUrl == null) {
            throw new IllegalArgumentException(String.format("%s property is not specified in property file", appUrlProperty));
        }
        return applicationUrl;
    }
}