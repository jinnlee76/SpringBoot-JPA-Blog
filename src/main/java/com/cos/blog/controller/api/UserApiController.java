package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.cos.blog.dto.ReponseDto;


@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user") 
	public ReponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("User API Controller 호출됨");
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요
		user.setRole(RoleType.USER);
		userService.addMember(user);
		return new ReponseDto<Integer>(HttpStatus.OK,1);
	}
}
