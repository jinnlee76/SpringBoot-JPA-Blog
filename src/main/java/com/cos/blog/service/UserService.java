package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(= loc를 해준다)
// 서비스의 필요성 : 2개 이상의 DB trx 을 하나의 trx로 묶어서 서비스화
@Service
public class UserService {
	
    @Autowired
	private UserRepository userRepository;
    
    // 1개의 transaction으로 관리 됨
    @Transactional
    public void addMember(User user) {
        userRepository.save(user);    				
    }
}