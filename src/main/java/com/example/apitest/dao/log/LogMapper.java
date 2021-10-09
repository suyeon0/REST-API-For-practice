package com.example.apitest.dao.log;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LogMapper {

    @Insert("INSERT INTO login_session(user_no,expire_time) VALUES(#{user_no}, )")
    void insertErrorLog(String msg);
}
