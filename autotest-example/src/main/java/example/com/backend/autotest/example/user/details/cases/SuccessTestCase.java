package example.com.backend.autotest.example.user.details.cases;

import org.springframework.stereotype.Component;

import example.com.backend.autotest.common.test.cases.TestCase;
import example.com.backend.autotest.example.user.details.GetUserDetailsTestData;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

@Component
public class SuccessTestCase implements TestCase<GetUserDetailsTestData> {

    @Override
    public String getTestCaseId() {
        return "success-test-case";
    }

    @Override
    public GetUserDetailsTestData generateAndPrepareTestData() {
        return new GetUserDetailsTestData("36");
    }

    @Override
    public void validate(
            GetUserDetailsTestData testData,
            Response response,
            SoftAssert softAssert) {
        Allure.step(
                "Validate response status code",
                () -> softAssert.assertEquals(200, response.getStatusCode())
        );
        Allure.step("Validate response body", () -> {
            softAssert.assertEquals(36, response.jsonPath().getInt("id"));
            softAssert.assertEquals("Bhilangana Kaur", response.jsonPath().getString("name"));
            softAssert.assertEquals(
                    "bhilangana_kaur@rippin.biz",
                    response.jsonPath().getString("email")
            );
            softAssert.assertEquals("female", response.jsonPath().getString("gender"));
            softAssert.assertEquals("inactive", response.jsonPath().getString("status"));
        });
    }

}
