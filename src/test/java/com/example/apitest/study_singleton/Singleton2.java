package com.example.apitest.study_singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Singleton2 {

    public static final Singleton2 INSTANCE = new Singleton2("Singleton2객체초기화");

    private Singleton2(String msg) {
        // private 생성자는 public static final 필드인 INSTANCE 를 초기화할때 딱 한번만 호출된다
        log.info(msg);
    }
}
