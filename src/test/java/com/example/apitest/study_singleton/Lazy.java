package com.example.apitest.study_singleton;

/**
 * 클래스의 인스턴스를 미리 생성하지 않고, 클래스 인스턴스가 사용되는 시점에 인스턴스를 만든다.
 * 하지만, 멀티쓰레드 방식에서 안전하지 않다는 문제가 있다.
 */
public class Lazy {

    private static Lazy instance;

    private Lazy() { }

    public static Lazy getInstance() {
        if (instance == null) {
            instance = new Lazy();
        }
        return instance;
    }
}
