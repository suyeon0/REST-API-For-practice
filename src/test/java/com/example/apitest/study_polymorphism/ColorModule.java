package com.example.apitest.study_polymorphism;

public class ColorModule implements PrinterModule{

    @Override
    public void init() {
        System.out.println("##ColorModule init()");
    }

    @Override
    public void print() {
        System.out.println("##ColorModule print()");
    }

    @Override
    public void sendEndMsg() {
        System.out.println("##ColorModule sendEndMsg()");
    }
}
