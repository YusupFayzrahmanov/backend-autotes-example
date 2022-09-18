package example.com.backend.autotest.common.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

/**
 * Утилиты для работы с JSON.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Десериализует строку в объект.
     *
     * @param json
     *         Строка.
     * @param type
     *         Тип.
     * @param <T>
     *         Тип.
     *
     * @return Объект.
     */
    @SneakyThrows
    public static <T> T deserializeJsonString(String json, Class<T> type) {
        return OBJECT_MAPPER.readValue(json, type);
    }

    /**
     * Десериализует в объект из файла.
     *
     * @param path
     *         Путь.
     * @param params
     *         Параметры.
     * @param type
     *         Тип.
     * @param <T>
     *         Тип.
     *
     * @return Объект.
     */
    public static <T> T deserializeFromFile(
            String path,
            Map<String, String> params,
            Class<T> type) {
        var json = FileUtils.readFileAndReplacePlaceholders(path, params);
        return deserializeJsonString(json, type);
    }

    /**
     * Сериализует объект в строку.
     *
     * @param value
     *         Объект.
     *
     * @return Строка.
     */
    @SneakyThrows
    public static String serialize(Object value) {
        return OBJECT_MAPPER.writeValueAsString(value);
    }

    /**
     * Сериализует объект в строку, если объект является строкой, то возвращает строку.
     *
     * @param value
     *         Объект.
     *
     * @return Строка.
     */
    @SneakyThrows
    public static String serializeWithStringCheck(Object value) {
        if (value instanceof String) {
            return (String) value;
        }
        return OBJECT_MAPPER.writeValueAsString(value);
    }

    /**
     * Валидирует по схеме.
     *
     * @param value
     *         Объект.
     * @param pathToSchema
     *         Путь до схемы.
     */
    public static void validateByJsonSchema(Object value, String pathToSchema) {
        var json = serialize(value);
        var jsonSchema = FileUtils.getBodyFromFile(pathToSchema);
        var jsonObject = new JSONObject(json);
        var schema = SchemaLoader.load(new JSONObject(jsonSchema));
        schema.validate(jsonObject);
    }

}
