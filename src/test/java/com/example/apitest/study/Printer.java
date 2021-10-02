package com.example.apitest.study;

public class Printer {

    private final PrinterModule module;

    public Printer(PrinterModule module) {
        this.module = module;
    }

    public void startPrint(){
        System.out.println(">> print start");
        module.init();
        module.print();
        module.sendEndMsg();
    }
}
