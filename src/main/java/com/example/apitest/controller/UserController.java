package com.example.apitest.controller;

import com.example.apitest.common.response.DefaultRes;
import com.example.apitest.common.util.FormatUtil;
import com.example.apitest.dto.UserDTO;
import com.example.apitest.service.UserService;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원 존재하는지 체크 (중복 체크)
     *
     * @param email
     * @return ResponseEntity
     */
    @GetMapping("/chk") //
    public ResponseEntity chkEmail(@Email @NotBlank @RequestParam String email) {
        DefaultRes res = this.userService.chkEmail(email);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 회원 정보 조회
     *
     * @param no
     * @return ResponseEntity
     */
    @GetMapping("/{no}")
    public ResponseEntity getUserInfoByNo(@PathVariable String no) {
        FormatUtil.chkNoFormat(no);
        DefaultRes res = this.userService.getUserInfoByNo(no);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 회원 가입(form)
     *
     * @param userRequest
     * @return ResponseEntity
     */
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity joinUserByForm(@Valid @ModelAttribute("userDTO") UserDTO userRequest) {
        FormatUtil.chkEmailFormat(userRequest.getEmail());
        DefaultRes res = this.userService.joinUser(userRequest);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    /**
     * 회원 가입(json)
     *
     * @param userRequest
     * @return ResponseEntity
     */
    @PostMapping("/join")
    public ResponseEntity<UserDTO> joinUserByJson(@Valid @RequestBody UserDTO userRequest) {
        FormatUtil.chkEmailFormat(userRequest.getEmail());
        DefaultRes res = this.userService.joinUser(userRequest);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    /**
     * 회원 정보 수정
     *
     * @param userRequest
     * @return ResponseEntity
     */
    @PostMapping(value = "/update/{no}")
    public ResponseEntity updateUserInfo(@PathVariable String no, @RequestBody UserDTO userRequest) {
        FormatUtil.chkNoFormat(no);
        if(ObjectUtils.isNotEmpty(userRequest.getEmail())){
            throw new IllegalArgumentException("email 수정 불가");
        }
        DefaultRes res = this.userService.updateUserInfo(no, userRequest);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 회원 정보 삭제
     *
     * @param no
     * @return ResponseEntity
     */
    @GetMapping("/del")
    public ResponseEntity deleteUser(@RequestParam String no) {
        FormatUtil.chkNoFormat(no);
        DefaultRes res = this.userService.deleteUser(no);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
