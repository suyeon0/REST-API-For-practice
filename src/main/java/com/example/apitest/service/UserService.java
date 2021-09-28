package com.example.apitest.service;

import com.example.apitest.common.response.DefaultRes;
import com.example.apitest.dto.UserDTO;
import java.util.HashMap;

public interface UserService {

    DefaultRes chkEmail(String email);
    DefaultRes getUserInfoByNo(String no);
    DefaultRes joinUser(UserDTO user);
    DefaultRes updateUserInfo(String no, UserDTO userRequest);
    DefaultRes deleteUser(String no);
    UserDTO getUserInfoByEmail(String email);
    HashMap<String, Object> loginChk(String email, String pwd);
}
