package example.com.backend.autotest.example.user.details.cases;

import org.springframework.stereotype.Component;

import example.com.backend.autotest.common.test.cases.TestCase;
import example.com.backend.autotest.example.user.details.GetUserDetailsTestData;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

@Component
public class NotFoundTestCase implements TestCase<GetUserDetailsTestData> {

    @Override
    public String getTestCaseId() {
        return "example-not-found-case";
    }

    @Override
    public GetUserDetailsTestData generateAndPrepareTestData() {
        return new GetUserDetailsTestData("-1");
    }

    @Override
    public void validate(
            GetUserDetailsTestData testData,
            Response response,
            SoftAssert softAssert) {
        Allure.step(
                "Validate response status code",
                () -> softAssert.assertEquals(404, response.getStatusCode())
        );
        Allure.step("Validate response body",
                    () -> softAssert.assertEquals(
                            "Resource not found",
                            response.jsonPath().getString("message")
                    )
        );
    }

}
