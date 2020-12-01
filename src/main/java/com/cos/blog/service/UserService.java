package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(= loc를 해준다)
// 서비스의 필요성 : 2개 이상의 DB trx 을 하나의 trx로 묶어서 서비스화
@Service
public class UserService {
	
    @Autowired
	private UserRepository userRepository;
    
    @Autowired // 주입
	private BCryptPasswordEncoder encode;
    
    
    // 1개의 transaction으로 관리 됨
    @Transactional
    public void addMember(User user) {
    	String rawPassword= user.getPassword();
    	String encPassword = encode.encode(rawPassword);
    	user.setPassword(encPassword);
    	user.setRole(RoleType.USER);
        userRepository.save(user);    				
    }
    
	/*
	 * @Transactional(readOnly=true) // Select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 보장)
	 * public User logIn(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword());
	 * 
	 * }
	 */
    @Transactional(readOnly = true)
    public User queryMember(String username) {
    	
    	User user = userRepository.findByUsername(username);
    	if (user == null)
    	      return new User();  	
    	return user;
    	
    }
    
    
    @Transactional
    public void updateUserInfo(User user) {
    	// 수정시에는 영속성 컨텍스트 User오브젝트를 영속성 시키고 영속화된 User오브젝트를 수정
    	// select 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화 하기 위해서 
    	// 영속화된 오브젝트를 변경하면 자동으로 DB에 udpate문을 날려주거든요
    	User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
    		return new IllegalArgumentException("회원찾기  실패");
    	});
    	
    	// Oauth 회원가입자 패스워드 변경 불가처리
    	if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
	    	String rawPassword= user.getPassword();
	    	String encPassword = encode.encode(rawPassword);
	    	persistance.setPassword(encPassword);
	    	persistance.setEmail(user.getEmail());
    	}
    	
    	System.out.println("[변경] 회원정보 변경(영속성 컨텍스트) :"+ user);
    	// 회원 수정 함수 종료시= 서비스 종료 = 트랜잭션 종료 = commit 자동 수행
    	// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌				
    }
}