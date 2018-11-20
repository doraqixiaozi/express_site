package com.express.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.express.pojo.LoginResult;
import com.express.pojo.User;

@Controller("/express_site")
public class UserController {
@RequestMapping("/login")
	public @ResponseBody LoginResult login(@RequestBody User user) {
	    System.out.println(user);
	
	
		return null;
		
	}

@RequestMapping("/signup")
public @ResponseBody LoginResult signup(@RequestBody User user) {
    System.out.println(user);


	return null;
	
}
}
