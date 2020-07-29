package org.example.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Configuration
@Profile({"unit"})
public class AWSTestConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3(){
        AmazonS3 s3Client = mock(AmazonS3.class);
        return s3Client;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS(){
        AmazonSQS sqsClient = mock(AmazonSQS.class);

        // stub getQueueUrl
        GetQueueUrlResult stubResult = mock(GetQueueUrlResult.class);
        when(sqsClient.getQueueUrl(anyString())).thenReturn(stubResult);
        return sqsClient;
    }
}