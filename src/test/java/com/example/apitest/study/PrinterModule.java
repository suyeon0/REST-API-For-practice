package com.example.apitest.study;

/**
 * 공통기능 인터페이스로 묶어놓음 -> 추상화
 * 인터페이스 역할 중 하나 : 명세
 */
public interface PrinterModule {
    void init();
    void print();
    void sendEndMsg();
}
