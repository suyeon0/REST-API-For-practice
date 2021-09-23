package com.example.apitest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Component
@Service
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(){
        log.info("welcome!");
        return "index";
    }

}
