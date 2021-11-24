package com.example.apitest.study_singleton;

/**
 * Enum 자체가 serialization, thread-safety 를 보장한다. 그래서 class 싱글톤에서 발생하는 단일 인스턴스 위반을 해결한다
 * (1) INSTANCE가 생성될때, multi thread로부터 안전합니다.(하지만, 내부 메소드는 thread-safe가 보장되지 않습니다.)
 * (2) 직렬화가 자동으로 처리되고 직렬화가 아무리 복잡하게 이루어져도 여러 객체가 생길일이 없습니다.
 * (3) 리플렉션(Reflection)을 통한 싱글톤 깨트림을 시도할 수 없습니다.
 *     private로 생성자를 선언해도 runtime에서 리플렉션(Reflection)을 통해 private 생성자에 접근을 할 수 있기때문에 싱글톤 깨트림 문제가 발생할 수 있습니다.
 */
public enum S5Enum {

    INSTANCE; //Instance 하나만 만들 수 있음.

    //enum은 런타임이 아닌 컴파일 타임에 모든 값을 알고 있어야 함 --> 생성자가 private 로 제한된다.
    private S5Enum() { }

}
