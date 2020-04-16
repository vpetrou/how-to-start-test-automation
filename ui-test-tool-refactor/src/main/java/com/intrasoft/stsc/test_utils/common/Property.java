package com.intrasoft.stsc.test_utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class Property {

    public static String getBrowser() {
        return getPropertyValue("app.browser");
    }

    public static String getURL() {
        return getPropertyValue("app.url");
    }

    public static String getApplicationPath() {
        return getPropertyValue("app.path");
    }

    public static String getApplicationOS() {
        return getPropertyValue("app.os");
    }

    private static String getPropertyValue(String propertyVariable) {
        String propertyValue = null;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(getPropertyFilePath());
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            propertyValue = prop.getProperty(propertyVariable);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return propertyValue;
    }

    private static String getPropertyFilePath() {
        String filePathString = getAbsolutePath() + "/src/main/resources/uitt.properties";
        File f = new File(filePathString);
        if (!f.exists())
            filePathString = getAbsolutePath() + "/uitt.properties";
        return filePathString;
    }

    private static String getAbsolutePath() {
        String absPath = Paths.get(".").toAbsolutePath().normalize().toString();
        String absolutePath = absPath.replace("\\", "/");
        return absolutePath;
    }

}
