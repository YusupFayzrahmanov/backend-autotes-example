package example.com.backend.autotest.example.user.details;

import org.springframework.beans.factory.annotation.Autowired;

import example.com.backend.autotest.common.test.cases.TestCase;
import example.com.backend.autotest.example.BaseSpringTest;
import example.com.backend.autotest.user.details.api.DefaultApi;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetUserDetailsTest extends BaseSpringTest {

    @Autowired
    private TestDataHolder dataHolder;

    private DefaultApi.GetUserOper api;

    @DataProvider(name = "getUserDetailsCases")
    public Object[][] getUserDetailsCases() {
        return dataHolder.getUserDetailsCases();
    }

    @BeforeMethod
    protected void setUp() {
        super.setUp();
        api = new DefaultApi.GetUserOper(requestSpecificationBuilder);
        api.respSpec(spec -> spec.addResponseSpecification(responseSpecBuilder.build()));
    }

    @Test(dataProvider = "getUserDetailsCases")
    public void testCasesRunner(TestCase<GetUserDetailsTestData> testCase) {
        var softAsser = new SoftAssert();
        var testData = Allure.step(
                "Prepare test data",
                () -> testCase.generateAndPrepareTestData()
        );
        api.idPath(testData.getId());
        var response = Allure.step(
                "Send request",
                () -> api.execute(apiResponse -> apiResponse.prettyPeek())
        );
        Allure.step("Validate test case", () -> testCase.validate(testData, response, softAsser));
    }

}
