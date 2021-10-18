package com.example.apitest.study_polymorphism;

import org.junit.jupiter.api.Test;

public class PrinterTest {

    @Test
    void printerTest() {
        Printer printer;
        PrinterModule module;
        int input = 2; // int input = System.in.read();
        if (input == 1) {
            module = new BlackAndWhiteModule(); // 인터페이스 다형성!
        } else {
            module = new ColorModule();
        }
        printer = new Printer();
        printer.setModule(module);
        printer.startPrint();
    }

}
