package org.testapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestUtils {
 private static final String propPath = "api-test.properties";

    public static InputStream getResourceFileAsInputStream(String fileName)
    {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static String getProperty(String prop)
    {
        Properties p = null;
        p = new TestUtils().loadPropertiesFile();
        return p.getProperty(prop);
    }

    //load properties file
    public Properties loadPropertiesFile()
    {
        Properties p = null;
        try
        {
            InputStream in = getResourceFileAsInputStream(propPath);
            p = new Properties();

            p.load(in);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return p;
    }
}
