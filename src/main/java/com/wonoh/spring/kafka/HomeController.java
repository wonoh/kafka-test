package com.wonoh.spring.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * /get?message=value 형태로 접근할 수 있도록 api 작성
     * @param message
     * @return
     */
    @RequestMapping(value="/get")
    public String getData(@RequestParam(value = "message", defaultValue = "") String message ){
        // 현재 시간
        LocalDateTime date = LocalDateTime.now();
        String dateStr = date.format(fmt);

        // mytopic에 현재 시간 + message를 produce 한다.
        kafkaTemplate.send("mytopic", dateStr + "   " + message);
        return "kafkaTemplate.send >>  " + message ;
    }


}
