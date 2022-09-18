package example.com.backend.autotest.example.settings;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@PropertySource("classpath:configuration.properties")
public class ServiceSettings {

    @Value("${host}")
    private String host;

    @Value("${request.timeout.ms}")
    private Integer requestTimeout;

}
