package example.com.backend.autotest.example;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.springframework.beans.factory.annotation.Autowired;

import example.com.backend.autotest.example.settings.ServiceSettings;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.Matchers.lessThan;

@SpringBootTest(classes = BootApplication.class)
public abstract class BaseSpringTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ServiceSettings configuration;

    protected RequestSpecBuilder requestSpecificationBuilder;

    protected ResponseSpecBuilder responseSpecBuilder;

    @BeforeSuite
    private void init() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    protected void setUp() {
        requestSpecificationBuilder = new RequestSpecBuilder()
                .setBaseUri(configuration.getHost())
                .addFilter(new AllureRestAssured())
                .setAccept("application/json")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecBuilder = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(configuration.getRequestTimeout().longValue()))
                .log(LogDetail.ALL);
    }

}
