package com.example.apitest.service;

import com.example.apitest.common.response.DefaultRes;
import com.example.apitest.dto.UserDTO;
import java.sql.SQLException;

public interface UserService {

    /**
     * 회원 email 중복 체크
     *
     * @param email
     * @return DefaultRes
     */
    DefaultRes chkEmail(String email);

    /**
     * 회원 정보, 조회 성공여부 리턴
     *
     * @param no
     * @return DefaultRes
     */
    DefaultRes getUserInfoByNo(String no);

    /**
     * 회원 등록
     *
     * @param user
     */
    DefaultRes joinUser(UserDTO user);

    /**
     * 회원 정보 수정, 수정 성공여부 리턴
     *
     * @param no
     * @param userRequest
     * @return DefaultRes
     */
    DefaultRes updateUserInfo(String no, UserDTO userRequest);

    /**
     * 회원 정보 삭제, 삭제 성공여부 리턴
     *
     * @param no
     * @return DefaultRes
     */
    DefaultRes deleteUser(String no);
}
