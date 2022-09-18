package example.com.backend.autotest.common.utils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.text.StringSubstitutor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    public static String readFileAndReplacePlaceholders(
            String path,
            Map<String, String> replaceValues) {
        String file = getBodyFromFile(path);
        return new StringSubstitutor(replaceValues, "{{", "}}").replace(file);
    }

    @SneakyThrows
    public static String getBodyFromFile(String path) {
        return org.apache.commons.io.FileUtils.readFileToString(
                new File(Objects.requireNonNull(
                        FileUtils.class.getClassLoader().getResource(path)
                ).getFile()),
                StandardCharsets.UTF_8
        );
    }

}
