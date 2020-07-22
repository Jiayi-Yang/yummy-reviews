package org.example.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AWSS3Service {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private AmazonS3 amazonS3;

    public AWSS3Service(@Autowired AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;
    }

    public Bucket createBucket(String bucketName) {
        Bucket bucket = null;
        if(!amazonS3.doesBucketExistV2(bucketName)) {
            bucket = amazonS3.createBucket(bucketName);
        } else {
            logger.info("bucket name: {} is not available."
                    + " Try again with a different Bucket name.", bucketName);
        }
        return bucket;
    }
    public void uploadFile(File f) throws IOException{
        // Upload a file as a new object with ContentType and title specified.
        String bucketName = "jyang-s3-bucket-test";
        PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
        amazonS3.putObject(request);

    }

}
