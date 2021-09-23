package com.example.apitest.common.util;

import java.util.regex.Pattern;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class FormatUtil {

    /**
     * userNo 포맷 체크
     *
     * @param no
     */
    public static void chkNoFormat(String no) {
        if (StringUtils.isBlank(no)) {
            throw new IllegalArgumentException("no 는 공백일 수 없습니다");
        } else if (!StringUtils.isNumeric(no)) {
            throw new IllegalArgumentException("no 는 숫자여야 합니다");
        }
    }

    /**
     * email 포맷 체크
     *
     * @param email
     */
    public static void chkEmailFormat(String email) {
        if (ObjectUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email is null");
        }
        String pattern = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,6}$";
        Boolean result = Pattern.matches(pattern, email);
        if (!result) {
            throw new IllegalArgumentException("email format 재확인 필요");
        }
    }
}
