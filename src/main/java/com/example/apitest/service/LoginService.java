package com.example.apitest.service;

import com.example.apitest.common.util.ResultString;
import com.example.apitest.common.util.TokenUtil;
import com.example.apitest.dao.login.LoginMapper;
import com.example.apitest.dto.UserDTO;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final LoginMapper loginMapper;

    /**
     * @param email
     * @param pwd
     * @return
     */
    public HashMap<String, Object> loginChk(String email, String pwd) {
        HashMap<String, Object> req = new HashMap<>();
        UserDTO dto = userService.getUserInfoByEmail(email);
        if (dto == null) {
            req.put("msg", ResultString.USER_INCORRECT_EMAIL);
        } else if (ObjectUtils.notEqual(pwd, dto.getPwd())) {
            req.put("msg", ResultString.USER_INCORRECT_PWD);
        } else {
            // 로그인 정보 세팅
            req.put("msg", ResultString.USER_LOGIN_SUCCESS);
            req.put("dto", dto);
        }
        return req;
    }

    /**
     * 로그아웃으로 인한 쿠키 삭제
     *
     * @param resp
     */
    public void delCookieByLogout(HttpServletResponse resp) {
        Cookie loginCookie = new Cookie("loginName", "");
        loginCookie.setPath("/");
        loginCookie.setMaxAge(0);
        resp.addCookie(loginCookie);

        Cookie loginTokenCookie = new Cookie("loginToken", "");
        loginTokenCookie.setPath("/");
        loginTokenCookie.setMaxAge(0);
        resp.addCookie(loginTokenCookie);
    }

    /**
     * 로그인 테이블 토큰 생성
     *
     * @param email
     * @return String token
     */
    public String generateLoginToken(String email) {
        long nowMillis = System.currentTimeMillis();
        String token = email + nowMillis;
        String encryptToken = null;
        try {
            encryptToken = tokenUtil.encrypt(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptToken;
    }

    /**
     * 로그인 성공시 로그인 토큰 쿠키 세팅
     *
     * @param email
     * @param resp
     */
    public void setLoginToken(String email, HttpServletResponse resp) {
        String token = this.generateLoginToken(email);
        this.insertLoginToken(token);
        Cookie loginCookie = new Cookie("loginToken", token);
        loginCookie.setPath("/"); //모든 경로에서 사용
        loginCookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(loginCookie);
    }

    /**
     * token 에 대해 DB 에서 유효성 체크후 만료시 true 리턴
     *
     * @param token
     * @return
     */
    public boolean isLoginTokenExpired(String token) {
        String result = loginMapper.isTokenExpired(token);
        log.info(">>>>>>>isLoginTokenExpired? : " + result);
        if (ObjectUtils.isEmpty(result)) { //expire 되지 않은 경우
            return false;
        } else {
            return true;
        }
    }

    /**
     * login token data DB 생성 로직
     *
     * @param token
     */
    public void insertLoginToken(String token) {
        // 새로운 토큰 유효시간 =  생성시간 + 설정해놓은 token 유효시간
        long expireTime = Long.parseLong(tokenUtil.getExpireTime());
        Date expirationDate = new Date(System.currentTimeMillis() + expireTime);
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(expirationDate.getTime());

        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expirationDate", sqlDate);

        loginMapper.insertLoginToken(map);
    }

}
