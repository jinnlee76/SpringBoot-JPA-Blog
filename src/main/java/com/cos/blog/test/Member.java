package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	
}
