package example.com.backend.autotest.common.test.cases;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

/**
 * Тест-кейс.
 *
 * @param <T>
 *         Тип тестовых данных.
 */
public interface TestCase<T> {

    /**
     * Возвращает идентификатор тест-кейса.
     */
    String getTestCaseId();

    /**
     * Генерирует и подготавливает тестовые данные.
     */
    T generateAndPrepareTestData();

    /**
     * Осуществляет проверку результата.
     *
     * @param testData
     *         Тестовые данные.
     * @param response
     *         Ответ.
     * @param softAssert
     *         Общая проверка.
     */
    void validate(T testData, Response response, SoftAssert softAssert);

}
