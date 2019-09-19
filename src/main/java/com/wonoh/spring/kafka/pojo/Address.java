package com.wonoh.spring.kafka.pojo;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address {

    private String city;
    private String detail;

    @Builder
    public Address(String city,String detail){
        this.city = city;
        this.detail = detail;
    }
}
