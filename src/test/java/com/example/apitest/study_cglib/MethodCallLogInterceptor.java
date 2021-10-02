package com.example.apitest.study_cglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/*
    MethodInterceptor 를 사용하면 원본 객체 메소드를 호출하기전 여러 작업 수행이 가능하다.
    메소드 호출 기록 남기는 로그 클래스 만드는걸로 연습해보기!
 */
public class MethodCallLogInterceptor implements MethodInterceptor {

    /*
        object : 원본 객체
        method : 원본 객체의 호출될 메소드를 나타내는 Method 객체
        args : 원본 객체에 전달될 파라미터
        methodProxy : CGLIB 가 제공하는 원본 객체의 메소드 프록시
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("#### before MemberServiceLogger.intercept()");
        Object returnValue = methodProxy.invokeSuper(object, args);
        System.out.println("#### after MemberServiceLogger.intercept()");
        return returnValue;
    }



}
