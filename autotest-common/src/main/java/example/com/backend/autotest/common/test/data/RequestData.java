package example.com.backend.autotest.common.test.data;

import java.util.Map;

/**
 * Данные для запроса.
 *
 * @param <T>
 *         Тип тела запроса.
 */
public interface RequestData<T> {

    /**
     * Возвращает тело запроса.
     */
    T getRequestBody();

    /**
     * Возвращает список заголовков запроса.
     */
    Map<String, String> getHeaders();

}
