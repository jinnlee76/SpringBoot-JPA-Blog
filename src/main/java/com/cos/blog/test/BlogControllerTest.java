package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.cos.blog패키지이하를 스캔하여 모든 파일을 메모리에 new 하지 않고
// 특정 어노테이션이 붙어 있는 클래스 파일을  new해서 loc 컨테이너에 관리해줌
@RestController 
public class BlogControllerTest {
	@GetMapping("/test/hello")
	public String Hello() {
		return "<h1>hello spring boot</h1>";
	}
}
