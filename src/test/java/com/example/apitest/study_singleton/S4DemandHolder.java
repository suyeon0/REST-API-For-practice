package com.example.apitest.study_singleton;

/**
 * jvm 의 클래스로더 매커니즘과 클래스의 load 시점을 사용한 방법
 * synchronized 키워드 없이 스레드 간의 동기화 문제 해결 가능
 *
 * DemandHolder 는 내부 클래스인 SingletonLazyHolder의 클래스 변수를 가지고 있지 않기 때문에
 * DemandHolder 클래스 로딩시 SingletonLazyHolder의 클래스를 초기화 하지 않는다
 *  -> classLoader 의 linking-preparing 과정과 initializing 과정 : static 변수에 대한 메모리 확보 및 초기화
 *  -> local inner class 는 특정 메서드 안에서 선언되는 지역변수와 같다. 메모리 생성 시점은 메소드가 호출될 때, 소멸 시점은 메소드가 종료될 때
 *
 * 따라서, SingletonLazyHolder의 초기화는
 * DemandHolder 클래스의 getInstance 메소드가 호출되고 SingletonLazyHolder의 INSTANCE 변수에 접근하는 순간 진행된다.
 *
 * 또한 클래스 초기화는 jvm 에 의해 이루어지기 때문에, jvm의 원자적특성을 이용하게 되고 이를 통해 thread-safe 가 보장된다.
 * https://bj25.tistory.com/21
 *
 */

public class S4DemandHolder {
    private S4DemandHolder(){}

    public static S4DemandHolder getInstance(){
        return SingletonLazyHolder.INSTANCE;
    }

    private static class SingletonLazyHolder{ // static class로 선언함으로써 외부에서 객체 생성 없이 호출 가능
        private static final S4DemandHolder INSTANCE = new S4DemandHolder();
    }
}
