package org.example.service;

import com.amazonaws.services.s3.model.Bucket;
import org.example.ApplicationBootstrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AWSS3Service awss3Service;
    @Test
    public void createBucketTest(){
        String bucketName = "jyang-s3-bucket-test1";
        Bucket bucket = awss3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }
}
