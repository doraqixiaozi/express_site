package com.express.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;
import com.express.service.MessageService;
import com.express.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;

	@RequestMapping("/login")
	public @ResponseBody LoginResult login(@Validated @RequestBody LoginForm loginForm, BindingResult result) {
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
		return login;

	}

	@RequestMapping("/signup")
	public @ResponseBody String signup(@Validated @RequestBody SignUpForm user, BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError objectError : list) {
				System.out.println(objectError.getDefaultMessage());
			}
			return "fail";
		}
		System.out.println(user);
		userService.doSignup(user);
		return "success";
	}
@RequestMapping("/similarEmail")
	public @ResponseBody List<String> getSimilarEmail(@RequestBody String email) {
	List<String> similarEmail = userService.getSimilarEmail(email);	
		return similarEmail;
	}
}
