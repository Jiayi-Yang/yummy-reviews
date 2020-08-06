package com;


import com.service.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.amazonaws.services.sqs.*;

@SpringBootApplication(scanBasePackages = "com")
public class ConsumerApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ConsumerApplication.class, args);
        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApplication.class, args);
        MessageService messageService = app.getBean(MessageService.class);
        messageService.receiveMessage();
    }
}
