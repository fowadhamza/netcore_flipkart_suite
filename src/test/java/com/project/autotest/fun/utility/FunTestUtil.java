package com.project.autotest.fun.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FunTestUtil {

    private static Properties PROPERTIES = null;

    /**
     * This method is used to load the funTestConfig.properties file
     */
    private static void loadFunPropertyFile() {
        String FILE_PATH = "funTestConfig.properties";
        PROPERTIES = new Properties();
        InputStream stream = FunTestUtil.class.getClassLoader().getResourceAsStream(FILE_PATH);
        if (stream == null) {
            System.err.println("WARNING: Properties file for functional tests does not exist: " + FILE_PATH + '.');
            return;
        }

        try {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            System.err.println("ERROR: Failure while reading properties file for functional tests: " + FILE_PATH + ':');
            e.printStackTrace(System.err);
        }
    }

    /**
     * This method is used to get a specific property from funTestConfig.properties
     * 
     * @param key	specific property
     * @return		value associated with the specific property
     */
    public static String getProperty(String key) {
        if (PROPERTIES == null) {
            loadFunPropertyFile();
        }
        return PROPERTIES.getProperty(key);
    }

}
