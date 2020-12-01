package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM
@Entity
public class Board {
	
	@Id // Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따른다.
	private int id; //시퀀스, auto_increment
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob
	private String content; //섬머노트 라이브리러리, html태그가 섞여서 디자인됨
	
	//@ColumnDefault("0")
	private int count;
	
	@ManyToOne(fetch=FetchType.EAGER) // Many = Board, User = One
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장 할 수 있다.
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER, cascade=CascadeType.REMOVE) 
	//연관관계의 주인이 아니다. 난 FK가 아니다, DB에 만들지말아라
	@JsonIgnoreProperties({"board"}) //board를 통해 접근하면 무한 루프를 방지
	@OrderBy(clause = "id desc")
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;	

}
