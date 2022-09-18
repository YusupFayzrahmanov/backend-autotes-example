package example.com.backend.autotest.common.rest.assured.filter;

import example.com.backend.autotest.common.utils.FileUtils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

/**
 * Фильтр валидации запросов по схеме.
 */
public class RequestJsonSchemaValidator implements Filter {

    private final Schema schema;

    public RequestJsonSchemaValidator(String pathToSchema) {
        var jsonSchema = FileUtils.getBodyFromFile(pathToSchema);
        schema = SchemaLoader.load(new JSONObject(jsonSchema));
    }

    @Override
    public Response filter(
            FilterableRequestSpecification filterableRequestSpecification,
            FilterableResponseSpecification filterableResponseSpecification,
            FilterContext filterContext) {
        try {
            schema.validate(new JSONObject(filterableRequestSpecification.getBody().toString()));
        } catch (RuntimeException e) {
            throw new IllegalStateException(
                    "Ошибка валидации запроса по схеме: " + e.getMessage(),
                    e
            );
        }
        return filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
    }

}
