package com.express.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;
import com.express.service.MessageService;
import com.express.service.UserService;
import com.sun.net.httpserver.Authenticator.Success;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;

	@RequestMapping("/login")
	public @ResponseBody LoginResult login(@Validated @RequestBody LoginForm loginForm, BindingResult result,HttpSession session) {
		List<String> errors = new ArrayList<>();
		LoginResult login = new LoginResult();
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError objectError : list) {
				System.out.println(objectError.getDefaultMessage());
				errors.add(objectError.getDefaultMessage());
			}
			login.setErrors(errors);
			return login;
		}
		System.out.println(loginForm);
		login = userService.login(loginForm);
		session.setAttribute("user", login.getUser());
		System.out.println(login);
		return login;

	}

	@RequestMapping("/signin")
	public @ResponseBody String signup(@Validated SignUpForm user, BindingResult result,MultipartFile multfile
,HttpServletRequest request) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError objectError : list) {
				System.out.println(objectError.getDefaultMessage());
			}
			return "error";
		}
		//重复校验
		List<String> similarEmail = userService.getSimilarEmail(user.getEmail());
		for (String string : similarEmail) {
			if (user.getEmail().equals(string)) {
				return "user has exist";
			}
		}				
		try {
			try {
				userService.doSignup(user,multfile,request);
			} catch (IllegalStateException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return "unkown error";
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return "not a image";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "user has exist";
		}		
		return "success";
	}

	@RequestMapping("/similarEmail")
	public @ResponseBody List<String> getSimilarEmail(@RequestBody SignUpForm form) {
		String email = form.getEmail();
		form = null;
		List<String> similarEmail = userService.getSimilarEmail(email);
		return similarEmail;
	}
	@RequestMapping("/searchfriend")
	public @ResponseBody User searchfriend(@RequestBody SignUpForm form) {
		String email = form.getEmail();
		form=null;
		User user = userService.searchfriend(email);
		return user;
	}
	
	@RequestMapping("/addfriend")
	public @ResponseBody String addfriend(@RequestBody User user,HttpSession session) {
     Integer f_id=user.getId();
     user = (User) session.getAttribute("user");
     if (user == null) {
		return "please login first";
	}
     Integer s_id=user.getId();     
     if (s_id==f_id) {
		return "you can not add yourself as friend";
	}
     userService.makeFriends(s_id,f_id);
     return "success";
	}
	
}
