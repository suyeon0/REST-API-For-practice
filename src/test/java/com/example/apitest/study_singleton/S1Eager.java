package com.example.apitest.study_singleton;

/**
 * static 을 사용하여 instance 변수를 인스턴스화와 상관없이 사용할 수 있도록 함
 * private 으로 외부 접근 차단
 * private 생성자이므로 외부에서 new 이용한 인스턴스 생성 불가
 * getInstance 이용해서만 instance 객체 가져올 수 있음.
 *
 * 문제점 > 1. new 키워드 때문에 클래스가 로드 되는 시점에서 인스턴스를 생성하는데,
 * 리소스가 큰 프로그램에서는 부담으로 작용할 수 있다 --> 불필요한 리소스 낭비 초래
 * 2. 클래스가 인스턴스화 되는 시점에 어떠한 에러 처리를 할 수 없는 문제
 */
public class S1Eager {

    private static S1Eager instance = new S1Eager();

    private S1Eager(){}

    public static S1Eager getInstance(){
        return instance;
    }
}
