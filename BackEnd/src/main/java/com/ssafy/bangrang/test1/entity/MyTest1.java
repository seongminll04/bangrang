package com.ssafy.bangrang.test1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyTest1 {
    @Id
    @GeneratedValue
    Long id;

    String title;

    @Builder
    public MyTest1(String title){
        this.title = title;
    }

}
