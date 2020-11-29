package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //Bean등록
public class PrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 떄, Username, Password 변수 2개를 가로채는데
	// Passwd부분은 알아서 처리
	// Username이 DB에 있는지만 확인해주면 됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		System.out.println("[유저등록] principal 정보 DB조회 via username : "+username);
		User principal = userRepository.findByUsername(username);
		if(principal == null) { 
			throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
		}
		System.out.println("[유저등록] DB조회 결과 via username, principal 정보 :"+principal);
		return new PrincipalDetail(principal); //시큐리티의 세션에 유저정보가 저장
	}
}
