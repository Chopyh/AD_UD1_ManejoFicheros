package jv.chopy.crud.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties() throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
                .getClassLoader().getResourceAsStream("application.properties");
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }

    public static String getProperty(String key) {
        try{
            Properties configuration = loadProperties();
            return configuration.getProperty(key);

        } catch (IOException e) {
            return null;
        }
    }

    public static void setProperty(String key, String value) {
        try {
            Properties configuration = loadProperties();
            configuration.setProperty(key, value);
            saveProperties(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveProperties(Properties properties) throws IOException {
        String resourcePath = PropertiesLoader.class.getClassLoader().getResource("application.properties").getFile();
        if (resourcePath == null) {
            throw new FileNotFoundException("Property file 'application.properties' not found in the classpath");
        }
        try (OutputStream outputStream = new FileOutputStream(resourcePath)) {
            properties.store(outputStream, null);
        }
    }
}