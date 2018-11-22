package com.express.advice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.express.pojo.LoginForm;
import com.express.pojo.SignUpForm;
import sun.misc.BASE64Encoder;

@Aspect
@Component
public class Advice {
//密码md5加密
	@Around("execution(public * com.express.service.UserServiceImpl.doSignup(..)) || execution(public * com.express.service.UserServiceImpl.login(..))")
	public Object passwordProcess(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		if (args.length == 1) {
			if (args[0] != null) {
				String password=null;
				String realPassword=null;
				if (args[0] instanceof SignUpForm) {
					SignUpForm user = (SignUpForm) args[0];
					password = user.getPassword();
					realPassword = makeMD5(password, realPassword);
					user.setPassword(realPassword);
				}
				if (args[0] instanceof LoginForm) {
					LoginForm user = (LoginForm) args[0];
					password = user.getPassword();
					realPassword = makeMD5(password, realPassword);
					user.setPassword(realPassword);
				}
			}
		}
		Object proceed = point.proceed();
		return proceed;
	}

private String makeMD5(String password, String realPassword) {
	try {
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] bs = md.digest(password.getBytes());
		BASE64Encoder base64Encoder = new BASE64Encoder();
		realPassword=base64Encoder.encode(bs);
		
	} catch (NoSuchAlgorithmException e) {
		System.out.println("加密失败");
		e.printStackTrace();
		throw new RuntimeException();
	}
	return realPassword;
}
	
	
}
