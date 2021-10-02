package com.example.apitest.study;

public class BlackAndWhiteModule implements PrinterModule{

    @Override
    public void init() {
        System.out.println("##BlackAndWhiteModule init()");
    }

    @Override
    public void print() {
        System.out.println("#BlackAndWhiteModule print()");
    }

    @Override
    public void sendEndMsg() {
        System.out.println("#BlackAndWhiteModule sendEndMsg()");
    }
}
