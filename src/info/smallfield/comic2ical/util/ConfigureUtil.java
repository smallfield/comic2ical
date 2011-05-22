package info.smallfield.comic2ical.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigureUtil {
    private static final ConfigureUtil instance = new ConfigureUtil();

    public static final String AMAZON_AWS_ACCESS_KEY_ID =
        "amazon.awsAccessKeyId";
    public static final String AMAZON_AWS_SECRET_KEY = "amazon.awsSecretKey";
    public static final String CRYPT_KEY = "crypt.key";

    private Properties configuration;

    private ConfigureUtil() {
        try {
            configuration = new Properties();
            InputStream inputStream =
                ConfigureUtil.class.getClassLoader().getResourceAsStream(
                    "config.properties");
            configuration.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return configuration.getProperty(key);
    }

    public static ConfigureUtil getInstance() {
        return ConfigureUtil.instance;
    }

}
