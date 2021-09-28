package com.example.apitest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LogMapper {

    @Insert("INSERT INTO error_log(msg) VALUES(#{msg})")
    void insertErrorLog(String msg);
}
