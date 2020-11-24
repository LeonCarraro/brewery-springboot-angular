package com.leoncarraro.breweryapi.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.leoncarraro.breweryapi.config.property.ApplicationProperty;
import com.leoncarraro.breweryapi.service.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;
    private final ApplicationProperty applicationProperty;

    public S3Service(AmazonS3 amazonS3, ApplicationProperty applicationProperty) {
        this.amazonS3 = amazonS3;
        this.applicationProperty = applicationProperty;
    }

    public URI uploadFile(String fileName, String contentType, InputStream inputStream) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);

            amazonS3.putObject(new PutObjectRequest(applicationProperty.getS3Security().getBucket(),
                    fileName, inputStream, objectMetadata));

            return amazonS3.getUrl(applicationProperty.getS3Security().getBucket(), fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI: " + e.getMessage());
        }
    }

}
