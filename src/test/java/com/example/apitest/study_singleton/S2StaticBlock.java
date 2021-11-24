package com.example.apitest.study_singleton;

/**
 * static 블럭 이용해서 최초 한번만 초기화하고, 예외처리 가능하게 된다. 하지만 리소스 낭비는 해결 x
 */
public class S2StaticBlock {

    private static S2StaticBlock instance;

    private S2StaticBlock() {
    }

    static {
        try {
            instance = new S2StaticBlock();
        } catch (Exception e) {
            throw new RuntimeException("creating instance exception 발생!");
        }
    }

    public static S2StaticBlock getInstance() {
        return instance;
    }
}
