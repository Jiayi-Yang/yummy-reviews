package org.example.config;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.Mockito.mock;


@Configuration
@Profile({"unit"})
public class AWSTestConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3(){
        AmazonS3 s3Client = mock(AmazonS3.class);
        return s3Client;
    }
}