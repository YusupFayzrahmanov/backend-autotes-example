package example.com.backend.autotest.common.test.data;

/**
 * Фабрика по созданию данных запроса.
 *
 * @param <T>
 *         Тип запроса.
 */
public interface RequestDataFactory<T> {

    /**
     * Создает данные запроса.
     */
    RequestData<T> create();

}
