package com.example.apitest.interceptor;

import com.example.apitest.service.LoginService;
import com.example.apitest.service.RedisService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;


@Component
@RequiredArgsConstructor
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private final LoginService loginService;
    private final RedisService redisService;

    /**
     * login interceptor - 로그인 토큰 정보 확인
     *
     * @param req
     * @param resp
     * @param handler
     * @return boolean
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Cookie loginCk = WebUtils.getCookie(req, "loginToken");
        try {
            // 쿠키에 로그인 정보 있는 경우
            if (loginCk != null) {
                log.info(">>>>>>>>>>로그인 쿠키 존재");
                String token = loginCk.getValue();
                log.info(">>>>>>>>>>>>>로그인 쿠키 토큰정보 : " + token);
                // db 테이블 확인해서 로그인 토큰에 대한 expire time 확인
                if (loginService.isLoginTokenExpired(token)) {
                    // 해당 토큰 무효할때
                    log.info(">>>>>>>>>>>>>로그인 쿠키 토큰 유효시간 끝남");
                    // (1) 로그인 쿠키 삭제
                    loginService.delCookieByLogout(resp);
                    // (2) 로그인 화면으로 리다이렉트
                    resp.sendRedirect("/user2/login");
                    return false;
                }
                log.info(">>>>>>>>>>로그인 유효함!");
                return true;
            } else {
                log.info(">>>>>>>>>>로그인 쿠키 없음");
                return true;
            }
        } catch (Exception e) {
            log.error("login interceptor exception 발생", e);
        }
        return true;
    }

}