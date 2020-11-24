package com.leoncarraro.breweryapi;

import com.leoncarraro.breweryapi.config.property.ApplicationProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(value = {ApplicationProperty.class})
public class BreweryApiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(BreweryApiApplication.class, args);
    }

}
