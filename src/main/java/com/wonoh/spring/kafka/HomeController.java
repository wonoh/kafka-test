package com.wonoh.spring.kafka;


import com.wonoh.spring.kafka.pojo.Account;
import com.wonoh.spring.kafka.pojo.Address;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HomeController {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final KafkaTemplate kafkaTemplate;

    public HomeController(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping(value="/send/{topic}/{message}")
    public String sendData(@PathVariable(value = "topic") String topic,
                          @PathVariable(value = "message") String message){
        // 현재 시간
        LocalDateTime date = LocalDateTime.now();
        String dateStr = date.format(fmt);

        // mytopic에 현재 시간 + message를 produce 한다.
        kafkaTemplate.send(topic, dateStr + "   " + message);
        return "kafkaTemplate.send >>  " + message ;
    }

    @RequestMapping(value = "/send/json")
    public String sendJson(){

        Account account = Account.builder()
                                .name("원오")
                                .age(25)
                                .address(Address.builder()
                                            .city("성남")
                                            .detail("미금")
                                            .build())
                                .build();

        kafkaTemplate.send("json_test",account);
        return "kafkaTemplate.send json " + account.toString();

    }


}
