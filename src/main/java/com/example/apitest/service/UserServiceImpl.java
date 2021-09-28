package com.example.apitest.service;

import com.example.apitest.common.exception.UserDupException;
import com.example.apitest.common.response.DefaultRes;
import com.example.apitest.common.response.ResponseMessage;
import com.example.apitest.common.util.ResultString;
import com.example.apitest.dao.UserMapper;
import com.example.apitest.dto.UserDTO;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 이메일 중복 검증
     *
     * @param email
     * @return
     */
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
    public DefaultRes joinUser(UserDTO user) {
        DefaultRes chkResult = this.chkEmail(user.getEmail());
        try {
            userMapper.joinUser(user);
            if (ObjectUtils.isEmpty(user.getNo())) { // auto-increment 대상인 no가 없는 경우
                throw new RuntimeException("No increments completed");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
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
        try {
            HashMap<String, Object> req = new HashMap<>();
            req.put("userNo", no);
            req.put("userRequest", userRequest);
            userMapper.updateUserInfo(req);
        } catch (Exception e) {
            throw new RuntimeException("updateUserInfo Exception 발생" + e.getCause());
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

    /**
     * email 로 가입된 회원 정보 불러온다
     *
     * @param email
     * @return
     */
    @Override
    public UserDTO getUserInfoByEmail(String email) {
        UserDTO user = userMapper.getUserInfoByEmail(email);
        return user;
    }

    /**
     * @param email
     * @param pwd
     * @return
     */
    @Override
    public HashMap<String, Object> loginChk(String email, String pwd) {
        HashMap<String, Object> req = new HashMap<>();
        UserDTO dto = getUserInfoByEmail(email);
        if (dto == null) {
            req.put("msg", ResultString.USER_INCORRECT_EMAIL);
        } else if (ObjectUtils.notEqual(pwd, dto.getPwd())) {
            req.put("msg", ResultString.USER_INCORRECT_PWD);
        } else {
            req.put("msg", ResultString.USER_LOGIN_SUCCESS);
            req.put("dto", dto);
        }
        return req;
    }

}
