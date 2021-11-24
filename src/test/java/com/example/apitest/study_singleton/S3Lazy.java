package com.example.apitest.study_singleton;

/**
 * 클래스의 인스턴스를 미리 생성하지 않고, 클래스 인스턴스가 사용되는 시점에 인스턴스를 만든다.
 * 하지만, 멀티쓰레드 방식에서 안전하지 않다는 문제가 있다.
 */
public class S3Lazy {

    private static S3Lazy instance;

    private S3Lazy() { }

    public static S3Lazy getInstance() {
        if (instance == null) {
            instance = new S3Lazy();
        }
        return instance;
    }

    // synchronized 붙여서 멀티쓰레드 해결할 수 있으나 수많은 접근 요청 들어왔을 때 속도 저하 성능 문제 발생.
    public static synchronized S3Lazy getInstance2(){
        if (instance == null){
            instance = new S3Lazy();
        }
        return instance;
    }
}
