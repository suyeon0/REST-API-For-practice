package com.example.apitest.study_singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Singleton {

    //1. static final 붙여서 한번만 초기화한다
    private static final Singleton INSTANCE = new Singleton("객체 초기화");

    private Singleton(String msg) {
        log.info(msg);
    }

    //2. public static 메소드로 다른 곳에서 호출할 수 있도록 하고, 호출하면 항상 같은 객체를 리턴한다
    public static Singleton getInstance() {
        return INSTANCE;
    }

}
