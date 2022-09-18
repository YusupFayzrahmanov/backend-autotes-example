package example.com.backend.autotest.common.validator;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

@FunctionalInterface
public interface Validator<T> {

    void validate(T testData, Response response, SoftAssert softAssert);

}
