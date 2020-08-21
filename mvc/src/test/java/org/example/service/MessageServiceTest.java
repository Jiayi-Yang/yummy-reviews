package org.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.example.ApplicationBootstrap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class MessageServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private  MessageService messageService;
    @Autowired
    private AmazonSQS sqsClient;

    @Before
    public void setup(){
        reset(sqsClient);
        GetQueueUrlResult stubResult = mock(GetQueueUrlResult.class);
        when(sqsClient.getQueueUrl(anyString())).thenReturn(stubResult);
    }

//    @After
//    public void tearDown(){
//        reset(sqsClient);
//    }

    @Test
    public void getQueueUrl(){
        messageService.getQueueUrl("123");
        verify(sqsClient,times(1)).getQueueUrl(anyString());
    }

    @Test
    public void sendMessageTest(){
       // when(sqsClient.getQueueUrl(anyString())).thenReturn(mock(GetQueueUrlResult.class));
        messageService.sendMessage("test",1);
        verify(sqsClient,times(1)).sendMessage(any(SendMessageRequest.class));
//        assertTrue(false);

    }
}
