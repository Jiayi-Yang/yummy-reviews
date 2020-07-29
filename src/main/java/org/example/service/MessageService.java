package org.example.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private AmazonSQS sqsClient;
    private String queueUrl;
    private String queueName = "yummy-standard-queue";

    public MessageService(@Autowired AmazonSQS sqsClient){
        this.sqsClient = sqsClient;
        this.queueUrl = getQueueUrl(queueName);
    }

    public String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = sqsClient.getQueueUrl(queueName);
        logger.info("QueueUrl: " + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

    public void sendMessage(String messageBody, Integer delaySec){
        this.queueUrl = getQueueUrl(queueName);
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySec);
        sqsClient.sendMessage(sendMsgRequest);

    }
}