package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @JmsListener(destination = "yummy-standard-queue")
    public void processMessage(String msg) throws IOException{
//        TODO convert input json string to Map, add send email/message
        System.out.println(msg);
    }
}
