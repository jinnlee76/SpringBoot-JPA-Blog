package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.dto.ReponseDto;


@RestController
public class UserApiController {
	@PostMapping("/api/user") 
	public ReponseDto<Integer> save(@RequestBody User user) {
		System.out.println("User API Controller 호출됨");
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요
		return new ReponseDto<Integer>(HttpStatus.OK,1);
	}
}
