package com.example.apitest.dao.login;

import java.util.HashMap;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {

    /**
     * token 유효 여부 확인
     *
     * @param token
     * @return
     */
    @Select("SELECT 1 FROM login_session WHERE token = #{token} AND expiration_date < now()")
    String isTokenExpired(String token);

    /**
     * token data 추가
     *
     * @param tokenInfo
     * @return
     */
    int insertLoginToken(HashMap<String, Object> tokenInfo);

    /**
     * token data 삭제
     *
     * @param token
     * @return
     */
    @Delete("DELETE FROM login_session WHERE token = #{token}")
    int deleteLoginToken(String token);
}
