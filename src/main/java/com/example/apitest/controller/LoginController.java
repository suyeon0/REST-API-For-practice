package com.example.apitest.controller;

import com.example.apitest.common.util.ResultString;
import com.example.apitest.dto.UserDTO;
import com.example.apitest.service.UserServiceImpl;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("user2")
public class LoginController {

    private final UserServiceImpl userService;

    /**
     * 로그인 페이지 이동
     *
     * @return page
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    /**
     * 로그인 결과 리턴 및 쿠키 설정
     *
     * @param req
     * @param params
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public int login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map<String, String> params) {
        String inputEmail = params.get("email");
        String inputPwd = params.get("pwd");
        try {
            HashMap<String, Object> result = userService.loginChk(inputEmail, inputPwd);
            ResultString msg = (ResultString) result.get("msg");
            UserDTO dto = (UserDTO) result.get("dto");
            if (msg.equals(ResultString.USER_INCORRECT_EMAIL)) {
                return 1;
            } else if (msg.equals(ResultString.USER_INCORRECT_PWD)) {
                return 2;
            } else {
                // 로그인 쿠키 생성
                Cookie loginCookie = new Cookie("loginName", dto.getName());
                loginCookie.setPath("/"); //모든 경로에서 사용
                loginCookie.setMaxAge(24 * 60);
                loginCookie.setDomain("local.test-flab.com");
                resp.addCookie(loginCookie);

                return 0;
            }
        } catch (Exception e) {
            return 9;
        }

    }

    /**
     * 로그아웃 진행 후 이전 페이지로 이동
     *
     * @param preURI
     * @return redirect page
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(@RequestParam(value = "preURI", required = false) String preURI, HttpServletResponse resp) {
        String nextURI = preURI;
        if (preURI == null || preURI.trim().equals("")) {
            nextURI = "/";
        }

        // 로그아웃시 쿠키 삭제
        Cookie loginCookie = new Cookie("loginName", "");
        loginCookie.setPath("/");
        loginCookie.setMaxAge(0);
        loginCookie.setDomain("local.test-flab.com");
        resp.addCookie(loginCookie);

        return "redirect:" + nextURI;
    }

}
