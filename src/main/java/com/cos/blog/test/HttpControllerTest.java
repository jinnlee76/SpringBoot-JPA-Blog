package com.cos.blog.test;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
	
	
	// 인터넷 브라우저를 통한 요청은 get요청만 가능
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get 요구 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@PostMapping("/http/post") // text/palin, application/json
	public String postTest(@RequestBody Member m) { // 스프링부트 메세지 컨버터
		return  "post 요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@PutMapping("/http/put") // update
	public String putTest(@RequestBody Member m) {
		return  "put 요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return  "delete 요청";
	}
}
