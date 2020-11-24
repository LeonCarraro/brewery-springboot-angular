package com.leoncarraro.breweryapi.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.leoncarraro.breweryapi.config.property.ApplicationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final ApplicationProperty applicationProperty;

    public S3Config(ApplicationProperty applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials =
                new BasicAWSCredentials(applicationProperty.getS3Security().getAccessKey(),
                                        applicationProperty.getS3Security().getSecretKey());

        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(applicationProperty.getS3Security().getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

}
