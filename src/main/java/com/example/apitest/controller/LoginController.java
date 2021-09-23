package com.example.apitest.controller;

import com.example.apitest.common.util.ResultString;
import com.example.apitest.dto.UserDTO;
import com.example.apitest.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user2")
public class LoginController {

    private final UserService userService;

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
     * 로그인 결과 리턴 및 세션 설정
     *
     * @param req
     * @param params
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public int login(HttpServletRequest req, @RequestParam Map<String, String> params) {
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
                HttpSession session = req.getSession();
                session.setAttribute("name", dto.getName());
                return 0;
            }
        } catch (Exception e) {
            return 9;
        }

    }

    /**
     * 로그아웃 진행 후 이전 페이지로 이동
     *
     * @param req
     * @param preURI
     * @return redirect page
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req, @RequestParam(value = "preURI", required = false) String preURI) {
        log.info("logout");
        String nextURI = preURI;
        if (preURI == null || preURI.trim().equals("")) {
            nextURI = "/";
        }
        HttpSession session = req.getSession();
        session.invalidate();

        return "redirect:" + nextURI;
    }

}
