package com.example.apitest.aop;

import com.example.apitest.common.util.FormatUtil;
import com.example.apitest.dto.UserDTO;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class UserParamAspect {

    /**
     * UserController - chkEmail : 실행 전 email Format 체크
     *
     * @param jp
     * @throws Throwable
     */
    @Before("execution(* com.example.apitest.controller.UserController.chkEmail(..))")
    public void chkEmail(JoinPoint jp) throws Throwable {
        String email = jp.getArgs()[0].toString();
        FormatUtil.chkEmailFormat(email);
    }

    /**
     * @param jp
     * @throws Throwable
     * @ EmailFormat 대상 : UserDTO 파라미터 내 email Format 체크
     */
    @Before("@annotation(com.example.apitest.annotation.EmailFormat)")
    public void chkEmailInUserDTO(JoinPoint jp) throws Throwable {
        String email = null;
        for (Object arg : jp.getArgs()) {
            if (arg instanceof UserDTO) {
                email = ((UserDTO) arg).getEmail();
            }
        }
        FormatUtil.chkEmailFormat(email);
    }

    /**
     * @param pjp
     * @throws Throwable
     * @ UserNoFormat 대상 : 파라미터 내 no 유효성 체크
     */
    @Around("@annotation(com.example.apitest.annotation.UserNoFormat)")
    public Object chkUserNo(ProceedingJoinPoint pjp) throws Throwable {
        String no = null;
        Object[] parameterValues = pjp.getArgs();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        for (int i = 0; i < method.getParameters().length; i++) {
            String parameterName = method.getParameters()[i].getName();
            if (parameterName.equals("no")) {
                no = (String) parameterValues[i];
                break;
            }
        }
        FormatUtil.chkNoFormat(no);

        Object result = pjp.proceed();

        return result;
    }
}
