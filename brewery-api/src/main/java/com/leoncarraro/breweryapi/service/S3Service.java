package com.leoncarraro.breweryapi.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.leoncarraro.breweryapi.service.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    @Value(value = "${s3.bucket}")
    private String bucket;

    private final Logger LOG = LoggerFactory.getLogger(AmazonS3.class);

    private final AmazonS3 amazonS3;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public URI uploadFile(String fileName, String contentType, InputStream inputStream) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);

            LOG.info("Iniciando upload...");
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
            LOG.info("Upload finalizado com sucesso.");

            return amazonS3.getUrl(bucket, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI: " + e.getMessage());
        }
    }

}
