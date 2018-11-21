package com.express.advice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.express.pojo.LoginForm;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;
import com.sun.javafx.scene.paint.GradientUtils.Point;

import sun.misc.BASE64Encoder;

@Aspect
@Component
public class Advice {
//密码md5加密
	@Before("execution(public * com.express.service.UserServiceImpl.doSignup(..)) || execution(public * com.express.service.UserServiceImpl.doLogin(..))")
	public void passwordProcess(ProceedingJoinPoint point) {
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
