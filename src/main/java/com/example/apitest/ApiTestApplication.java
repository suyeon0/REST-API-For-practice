package com.example.apitest;

import com.example.apitest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ApiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTestApplication.class, args);
    }

    @Controller
    @Slf4j
    public static class HomeController {

        @RequestMapping("/")
        public String index(){
            log.info("Welcome home!");
            return "index";
        }

    }

    @Controller
    @RequiredArgsConstructor
    public static class LoginController {

        private final UserService userService;

        @GetMapping("/login_form")
        public String loginForm(){
            return "login/login";
        }

        @PostMapping("/login")
        public String doLogin(@RequestParam String email, String pwd){
            return "";
        }

    }
}
