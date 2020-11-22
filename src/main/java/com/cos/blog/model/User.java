package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;

import lombok.Data;
@Data
// ORM -> JAVA 다른언어 Object -> 테이블로 매핑해주는 기술
// @DynamicInsert  // Insert가 null인 필드는 제외
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따른다.
	private int id; //시퀀스, auto_increment
	
	@Column(nullable=false, length =30)
	private String username;
	
	@Column(nullable=false, length=100) //123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ ColumnDefault("'user'")
	// DB는 ruletype이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는 것이 좋다. admin, user, manager
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
}
