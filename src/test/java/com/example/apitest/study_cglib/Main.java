package com.example.apitest.study_cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class Main {

    @Test
    public void makesProxyNoOp(){
        // 1.Enhancer 객체를 생성한다
        Enhancer enhancer = new Enhancer();

        // 2. setSuperclass() 메소드에 프록시할 클래스를 지정
        enhancer.setSuperclass(MemberServiceImpl.class);
        enhancer.setCallback(NoOp.INSTANCE);
        //NoOp는 아무 작업도 수행하지 않고 곧바로 원본 객체를 호춯하는 콜백이다!

        // 3. enhancer.create() 로 프록시 생성
        Object obj = enhancer.create();

        // 4. 프록시를 통해서 간접 접근
        MemberServiceImpl memberService = (MemberServiceImpl) obj;
        memberService.regist(new Member());
        memberService.getMember("test");

        /**
         * [정리]
         * enhancer.create() 로 생성한 객체는 프록시 객체가 되고,
         * 이 객체의 메소드를 호출하게 되면 프록시 객체를 거쳐서
         * 실제 객체의 메소드가 호출된다.
         *
         * 실행 결과를 보면 직접 MemberServiceImpl 객체를 생성해서 실행하는 것과
         * 별반 차이가 없어보인다.
         * 이는 프록시 객체가 단순히 원본 객체의 메소드를 직접적으로 호출하기 때문이다.
         * 하지만, 대부분의 프록시 객체는 원본 객체에 접근하기 전에 별도의 작업을 수행하며
         * CGLIB는 Callback을 사용해서 별도 작업을 수행할 수 있도록 하고 있다.
         * ( 가장 많이 사용하는 것 : net.sf.cglib.proxy.MethodInterceptor )
         *
         * https://javacan.tistory.com/entry/114 참고.
         */

    }

    @Test
    public void makesProxyMethodInterceptor(){
        // 1.Enhancer 객체를 생성한다
        Enhancer enhancer = new Enhancer();

        // 2. setSuperclass() 메소드에 프록시할 클래스를 지정
        enhancer.setSuperclass(MemberServiceImpl.class);
        // 3. MethodInterceptor 사용
        enhancer.setCallback(new MethodCallLogInterceptor());

        // 4. enhancer.create() 로 프록시 생성
        Object obj = enhancer.create();

        // 5. 프록시를 통해서 간접 접근
        MemberServiceImpl memberService = (MemberServiceImpl) obj;
        memberService.regist(new Member());
        memberService.getMember("test");

        // 6. 실행결과
//        # create MemberServiceImpl!
//        #### before MemberServiceLogger.intercept()
//        # MemberServiceImpl.regist
//        #### after MemberServiceLogger.intercept()
//        #### before MemberServiceLogger.intercept()
//        # MemberServiceImpl.getMember:test
//        #### after MemberServiceLogger.intercept()
    }
}
