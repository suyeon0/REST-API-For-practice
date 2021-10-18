package com.example.apitest.study_atomic;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class RuntimeTest {

    @Test
    public void testAtomic() {
        long beforeTime = System.currentTimeMillis();

        AtomicInteger integer = new AtomicInteger(1);
        for (int i = 0; i < 100000; i++) {
            integer.incrementAndGet();
        }

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("AtomicInteger 시간차이 : " + secDiffTime);
    }

    @Test
    public void testNormal() {
        long beforeTime = System.currentTimeMillis();

        int num = 0;
        for (int i = 0; i < 100000; i++) {
            num++;
        }

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("int 시간차이 : " + secDiffTime);
    }

    @Test
    /**
     * Atomic 변수와 일반 변수 메소드 실행속도 측정해보기
     */
    public void test(){
        this.testAtomic();
        this.testNormal();
    }
}
