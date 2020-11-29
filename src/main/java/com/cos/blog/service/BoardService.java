package com.cos.blog.service;



import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(= loc를 해준다)
// 서비스의 필요성 : 2개 이상의 DB trx 을 하나의 trx로 묶어서 서비스화
@Service
public class BoardService {
	
    @Autowired
	private BoardRepository boardRepository;
    
  
    
    // 1개의 transaction으로 관리 됨
    @Transactional
    public void writeBoard(Board board, User user) {
    	board.setCount(0);
    	board.setUser(user);
    	boardRepository.save(board);    				
    }
    
    
    public Page<Board> listBoard(Pageable pageable){
    	return boardRepository.findAll(pageable);
    }
    
    
    @Transactional(readOnly=true)
    public Board readDetailBoard(int id) {
    	return boardRepository.findById(id)
    			.orElseThrow(()->{
    				return new IllegalAddException("글 상세보기 실패");
    			});
    }
    
    @Transactional
    public void deleteBoard(int id) {
    	boardRepository.deleteById(id);
    }
    
    @Transactional
    public void updateBoard(int id,Board requestBoard) {
      Board board = boardRepository.findById(id)
    		  .orElseThrow(()->{
  				return new IllegalAddException("글 찾기 실패");
  			});  
      board.setTitle(requestBoard.getTitle());
      board.setContent(requestBoard.getContent());
      // 해당 함수로 종료시 트랜잭션이 종료됩니다. 이때 더티체킹- 자동update가 됨, db flush
    }
}