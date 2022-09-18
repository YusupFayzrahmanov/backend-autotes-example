package example.com.backend.autotest.common.test.data;

import java.util.HashMap;
import java.util.Map;

import example.com.backend.autotest.common.utils.JsonUtils;
import example.com.backend.autotest.common.utils.PropertiesUtils;

import lombok.Getter;

@Getter
public class SimpleRequestData<T> implements RequestData<T> {

    private final T requestBody;

    private final Map<String, String> headers;

    public SimpleRequestData(
            T requestBody,
            Map<String, String> headers) {
        this.requestBody = requestBody;
        this.headers = new HashMap<>(headers);
    }

    public static <T> SimpleRequestData<T> createFromPropertiesAndTemplate(
            String pathToTemplate,
            String pathToTemplateProperties,
            String pathToHeaders,
            Class<T> requestType) {
        var templateParameters = PropertiesUtils.propertiesFromFile(pathToTemplateProperties);
        var request = JsonUtils.deserializeFromFile(
                pathToTemplate,
                templateParameters,
                requestType
        );
        var headers = PropertiesUtils.propertiesFromFile(pathToHeaders);
        return new SimpleRequestData<>(request, headers);
    }

}
