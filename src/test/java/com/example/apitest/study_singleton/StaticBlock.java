package com.example.apitest.study_singleton;

/**
 * static 블럭 이용해서 최초 한번만 초기화하고, 예외처리 가능하게 된다. 하지만 리소스 낭비는 해결 x
 */
public class StaticBlock {

    private static StaticBlock instance;

    private StaticBlock() {
    }

    static {
        try {
            instance = new StaticBlock();
        } catch (Exception e) {
            throw new RuntimeException("creating instance exception 발생!");
        }
    }

    public static StaticBlock getInstance() {
        return instance;
    }
}
