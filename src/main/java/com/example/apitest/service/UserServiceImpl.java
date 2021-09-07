package com.example.apitest.service;

import com.example.apitest.common.exception.UserDupException;
import com.example.apitest.common.response.DefaultRes;
import com.example.apitest.common.response.ResponseMessage;
import com.example.apitest.dao.UserMapper;
import com.example.apitest.dto.UserDTO;
import java.util.ArrayList;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public DefaultRes chkEmail(String email) {
        String chkID = userMapper.chkId(email);
        // 가입 불가 - 해당 email 유저가 가입 되어 있는 경우
        if (StringUtils.isNotBlank(chkID)) {
            throw new UserDupException(email);
        }
        // 가입 가능
        DefaultRes res = DefaultRes.builder()
            .statusCode(HttpStatus.OK.toString())
            .responseMessage(ResponseMessage.CHK_SUC_USER)
            .data(email)
            .build();
        return res;
    }

    @Override
    public DefaultRes getUserInfoByNo(String no) {
        UserDTO result = new UserDTO();
        UserDTO user = userMapper.getUserInfoByNo(no);
        DefaultRes res = null;
        if (ObjectUtils.isEmpty(user)) {
            res = DefaultRes.builder()
                .statusCode(HttpStatus.OK.toString())
                .responseMessage(ResponseMessage.NOT_FOUND_USER)
                .data(new ArrayList())
                .build();
        } else {
            res = DefaultRes.builder()
                .statusCode(HttpStatus.OK.toString())
                .responseMessage(ResponseMessage.GET_USER)
                .data(user)
                .build();
        }
        return res;
    }

    @Override
    public DefaultRes joinUser(UserDTO user){
        DefaultRes chkResult = this.chkEmail(user.getEmail());
        try{
            userMapper.joinUser(user);
        }catch(Exception e){
            throw new RuntimeException("joinUser Exception 발생");
        }
        DefaultRes res = DefaultRes.builder()
            .statusCode(HttpStatus.CREATED.toString())
            .responseMessage(ResponseMessage.CREATED_USER)
            .data(user)
            .build();
        return res;
    }

    @Override
    public DefaultRes updateUserInfo(String no, UserDTO userRequest) {
        DefaultRes userInfo = this.getUserInfoByNo(no);
        int cnt = userMapper.updateUserInfo(userRequest);
        if (cnt < 1){
            throw new RuntimeException("updateUserInfo Exception 발생");
        }
        DefaultRes res = DefaultRes.builder()
            .statusCode(HttpStatus.OK.toString())
            .responseMessage(ResponseMessage.UPDATE_USER)
            .data(userRequest)
            .build();
        return res;
    }

    @Override
    public DefaultRes deleteUser(String no) {
        DefaultRes userInfo = this.getUserInfoByNo(no);
        userMapper.deleteUser(no);
        DefaultRes res = DefaultRes.builder()
            .statusCode(HttpStatus.OK.toString())
            .responseMessage(ResponseMessage.DELETE_USER)
            .data(userInfo.getData())
            .build();
        return res;
    }
}
