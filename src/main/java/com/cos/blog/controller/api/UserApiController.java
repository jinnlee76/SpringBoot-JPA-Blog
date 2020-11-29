package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;


@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/auth/joinProc") 
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("[가입] User API Controller : Save 호출!!");
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요
		userService.addMember(user);
		System.out.println("[가입] User API Controller : Save 결과!!");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
			}
	
			/*
			 * @PostMapping("/api/user/login") public ReponseDto<Integer> login(@RequestBody
			 * User user, HttpSession session){
			 * System.out.println("User API Controller : login 호출됨"); User principal;
			 * principal = userService.logIn(user);
			 * 
			 * if(principal != null) { session.setAttribute("principal",principal); } else {
			 * System.out.println("로그인 실패 "); } return new
			 * ReponseDto<Integer>(HttpStatus.OK,1); }
			 */
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		System.out.println("[변경] 회원정보 변경 전(서비스): " + user);
		userService.updateUserInfo(user);
		System.out.println("[변경] 회원정보 변경 완료(서비스)");
		// 여기서 트랜잭션이 종료되기 때문에 DB의 값음 변경이되나
	   // 하지만 세션값은 변경되지 않은 상태임  
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
}
