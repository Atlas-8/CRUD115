package util;

import java.io.*;
import java.util.Properties;

public class PropertyReader {
    public static Properties read() throws IOException {
        Properties properties = new Properties();
        try (InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
        }
        return properties;
    }
}
