package com.example.apitest.study_cglib;

public class MemberServiceImpl implements MemberService{

    public MemberServiceImpl() {
        System.out.println("# create MemberServiceImpl!");
    }

    @Override
    public void regist(Member member) {
        System.out.println("# MemberServiceImpl.regist");
    }

    @Override
    public Member getMember(String id) {
        System.out.println("# MemberServiceImpl.getMember:" + id);
        return new Member();
    }
}
