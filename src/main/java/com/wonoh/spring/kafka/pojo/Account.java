package com.wonoh.spring.kafka.pojo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Account {

    private String name;
    private int age;
    private Address address;

    @Builder
    public Account(String name,int age,Address address){
        this.name = name;
        this.address = address;
        this.age = age;
    }

}
