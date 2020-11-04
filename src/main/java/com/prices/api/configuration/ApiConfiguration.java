package com.prices.api.configuration;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableJpaRepositories(basePackages = "com.prices.api.repository")
public class ApiConfiguration implements WebMvcConfigurer {

  @Bean
  public ObjectMapper springMvcObjectMapper() {
    return new ObjectMapper()
        .setPropertyNamingStrategy(SNAKE_CASE)
        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(NON_NULL)
        .configure(FAIL_ON_EMPTY_BEANS, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))
        .registerModule(new JavaTimeModule())
        .registerModule(new Jdk8Module());
  }

  @Bean
  public OpenAPI customOpenAPI() {
    OpenAPI oa = new OpenAPI()
        .info(new Info()
            .title("Prices API")
            .contact(new Contact()
                .url("https://github.com/nicove68")
                .name("Nico Valerga"))
            .version("v1.0.0")
            .description("Basic API Rest for product prices")
        );
    ModelConverters.getInstance().addConverter(new ModelResolver(springMvcObjectMapper()));
    return oa;
  }
}
