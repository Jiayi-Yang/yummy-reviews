package org.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.example.ApplicationBootstrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private AWSS3Service awss3Service;
    String filePath = "/Users/jay/Downloads/logo.png";
    String bucketName = "jyang-s3-bucket-test";

    @Test
    public void createBucketTest(){
        String bucketName = "jyang-s3-bucket-test2";
        Bucket bucket = awss3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }

    @Test
    public void uploadFileTest() throws IOException{
        awss3Service.uploadFile(new File(filePath));
        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class));
    }

//    @Test
//    public void uploadFileUUIDTest() throws IOException{
//        awss3Service.uploadFileUUID(bucketName, new MultipartFile(filePath) {
//        });
//        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class));
//    }

}
