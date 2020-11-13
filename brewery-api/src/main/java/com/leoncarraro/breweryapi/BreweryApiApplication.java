package com.leoncarraro.breweryapi;

import com.leoncarraro.breweryapi.service.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BreweryApiApplication implements CommandLineRunner {

    private final S3Service s3Service;

    public BreweryApiApplication(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(BreweryApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        s3Service.uploadFile("C:\\Users\\lcarr\\Desktop\\patagonia-473.png");
    }

}
