package example.com.backend.autotest.common.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesUtils {

    @SneakyThrows
    public static Map<String, String> propertiesFromFile(String path) {
        var properties = new Properties();
        var loader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = loader.getResourceAsStream(path)) {
            properties.load(input);
        }
        return new HashMap<>((Map) properties);
    }

}
