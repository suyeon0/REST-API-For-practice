package com.example.apitest.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Service
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest req, @CookieValue(value = "loginName", required = false) Cookie loginCk) {
        log.info("welcome!");

        if (loginCk != null) {
            String loginName = loginCk.getValue();
            req.setAttribute("loginName", loginName);
        }

        return "index";
    }



}
