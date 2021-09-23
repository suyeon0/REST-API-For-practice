package com.example.apitest.dao;

import com.example.apitest.dto.UserDTO;
import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 회원 테이블 등록 여부 확인
     *
     * @param email
     * @return
     */
    @Select("SELECT email FROM user WHERE EMAIL = #{email}")
    String chkId(String email);

    /**
     * 회원 정보 조회
     *
     * @param no
     * @return UserDTO
     */
    UserDTO getUserInfoByNo(String no);

    /**
     * 회원 정보 조회 ( 로그인시 이메일로 )
     * @param email
     * @return UserDTO
     */
    UserDTO getUserInfoByEmail(String email);

    /**
     * 회원 등록
     *
     * @param user
     */
    @Options(useGeneratedKeys = true, keyProperty = "no")
    int joinUser(UserDTO user);

    /**
     * 회원 수정
     *
     * @param userRequest
     */
    int updateUserInfo(HashMap<String, Object> userRequest);

    /**
     * 회원 삭제
     *
     * @param no
     */
    int deleteUser(String no);

}
