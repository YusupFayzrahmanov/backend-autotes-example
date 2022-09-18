package example.com.backend.autotest.example.user.details;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import example.com.backend.autotest.common.test.cases.TestCase;

@Component
public class TestDataHolder {

    @Autowired
    private List<TestCase<GetUserDetailsTestData>> cases;

    public Object[][] getUserDetailsCases() {
        return cases.stream()
                    .map(testCase -> new Object[] {testCase})
                    .collect(Collectors.toList())
                    .toArray(new Object[][] {});
    }

}
