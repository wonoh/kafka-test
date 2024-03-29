package com.wonoh.spring.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "mytopic")
    public void listen(@Payload String message){
        log.info("메시지= '{}'",message);
    }

}
