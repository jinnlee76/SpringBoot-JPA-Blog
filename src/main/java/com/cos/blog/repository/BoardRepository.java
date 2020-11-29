package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;


// DAO
// 자동으로 Bean으로 등록됨
@ Repository //생략가능
public interface BoardRepository extends JpaRepository<Board,Integer> {
	//Select * from user where username =1?; 
	//@Query (value = "SELECT u. * uROM에서 u.username =? 1", nativeQuery = true)
	//User findByUsername(String username);
	//User findByUsername(String username);
	
	//@Query(value="SELECT u.* FROM user u WHERE u.username=?") 
	//User findByUsername(String username);
	//JPA 네이밍 쿼리 전략
	// select * from user where username= ? and password = ?;
	//User findByUsernameAndPassword(String username,String password);

	/*
	 * @Query(value = "select * from user where username= ? and password = ?;") User
	 * User login(String username, String password);
	 */
}
