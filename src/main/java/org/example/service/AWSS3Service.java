package org.example.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AWSS3Service {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private AmazonS3 amazonS3;
    String bucketName = "jyang-s3-bucket-test";

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
        // Upload a file as a new object with raw file name
        PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
        amazonS3.putObject(request);

    }
    public String uploadFileUUID(String bucketName, MultipartFile f) throws IOException{
        // Upload a file as a new object with hashed file name
        String originalName = f.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String[] originalNameArray = originalName.split("\\.");
        String hashedName = originalNameArray[0] + uuid + "." + originalNameArray[1];
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(f.getContentType());
        objectMetadata.setContentLength(f.getSize());
        amazonS3.putObject(bucketName, hashedName, f.getInputStream(), objectMetadata);
        return hashedName;
    }

    public String getURL(String filename){
        String URL = String.valueOf(amazonS3.getUrl(
                bucketName, //The S3 Bucket To Upload To
                filename));
        return URL;
    }
}

