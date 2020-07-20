package org.example.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AWSS3Service {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private AmazonS3 amazonS3;

    public AmazonS3 getAmazonS3() {
        return amazonS3;
    }

    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
    public AWSS3Service(){
        amazonS3 = getS3ClientUsingDefaultChain();
    }

//    private String myAWSAccessKeyId="AKIA4IYBBYERTPULKG6L";
//    private String myAWSSecretKey="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";
    private AmazonS3 getS3ClientWithSuppliedCredentials() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("aws.accessKeyId", "aws.secretKey");
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        return s3Client;
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

    private AmazonS3 getS3ClientUsingDefaultChain() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        return s3Client;
    }

}
