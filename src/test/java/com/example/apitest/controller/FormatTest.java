package com.example.apitest.controller;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormatTest {

    public boolean chkEmailFormat(String email){
        String pattern = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(pattern, email);
    }

    @Test
    public void emailTest(){
        String email1 = "test@test.com";
        String email2 = "test123@test.com";
        String email3 = "test123test.test@test.com";
        String email4 = "test-123_test@test.com";
        String email5 = "test-123_test@test-123.com";
        String email6 = "test-123_test@test-123.net";
        String email7 = "test-123_test@test-123.kr";
        String email8 = "test-123_test@test-123.asia";

        Assertions.assertTrue(this.chkEmailFormat(email1));
        Assertions.assertTrue(this.chkEmailFormat(email2));
        Assertions.assertTrue(this.chkEmailFormat(email3));
        Assertions.assertTrue(this.chkEmailFormat(email4));
        Assertions.assertTrue(this.chkEmailFormat(email5));
        Assertions.assertTrue(this.chkEmailFormat(email6));
        Assertions.assertTrue(this.chkEmailFormat(email7));
        Assertions.assertTrue(this.chkEmailFormat(email8));

    }
}
