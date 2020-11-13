package com.leoncarraro.breweryapi.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    @Value(value = "${s3.bucket}")
    private String bucket;

    private final Logger LOG = LoggerFactory.getLogger(AmazonS3.class);

    private final AmazonS3 amazonS3;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);

            LOG.info("Starting upload...");
            amazonS3.putObject(new PutObjectRequest(bucket, "test-upload.png", file));
            LOG.info("File uploaded");
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException >>> " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOG.info("AmazonClientException >>> " + e.getMessage());
        }
    }

}
