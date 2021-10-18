package com.example.apitest.study_polymorphism;

public class Printer {

    private PrinterModule module;

    public void setModule(PrinterModule module){
        this.module = module;
    }

    public void startPrint(){
        System.out.println(">> print start");
        module.init();
        module.print();
        module.sendEndMsg();
    }
}
