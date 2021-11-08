package com.example.apitest.study_singleton;

/**
 * jvm 의 클래스로더 매커니즘과 클래스의 load 시점을 사용한 방법
 * synchronized 키워드 없이 스레드 간의 동기화 문제 해결 가능
 *
 * DemandHolder 는 내부 클래스인 SingletonLazyHolder의 클래스 변수를 가지고 있지 않기 때문에
 * DemandHolder 클래스 로딩시 SingletonLazyHolder의 클래스를 초기화 하지 않는다
 *
 * DemandHolder 클래스의 getInstance 메소드가 호출되고 SingletonLazyHolder의 INSTANCE 변수에 접근하는 순간,
 * SingletonLazyHolder의 클래스의 초기화가 진행된다.
 * 클래스 초기화는 jvm 에 의해 이루어지기 때문에, jvm의 원자적특성을 이용하게 되고 이를 통해 thread-safe 가 보장된다.
 * https://bj25.tistory.com/21
 *
 */

public class DemandHolder {
    private DemandHolder(){}

    public static DemandHolder getInstance(){
        return SingletonLazyHolder.INSTANCE;
    }

    private static class SingletonLazyHolder{
        private static final DemandHolder INSTANCE = new DemandHolder();
    }
}
