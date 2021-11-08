package com.example.apitest.study_singleton;

public class Test {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton);
        System.out.println(singleton1);
        System.out.println(singleton2);

        System.out.println("=============================");
        System.out.println(Singleton2.INSTANCE);
        System.out.println(Singleton2.INSTANCE);

        System.out.println("=============================");
        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;

    }


}
