package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board") 
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) { 
		System.out.println("Board API Controller : Save 호출!!"+board.getTitle());
		boardService.writeBoard(board, principal.getUser());
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요

		System.out.println("Board API Controller : Save 결과!!");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
			}
	
	// 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	// dto 사용하지 않는 이유는!!
	@PostMapping("/api/board/{boardId}/reply") 
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) { 
		System.out.println("[댓글] API Controller : Save 호출!!");
         boardService.writeReply(replySaveRequestDto);
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요
		System.out.println("[댓글] API Controller : Save 결과!!");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
			}
	
	@DeleteMapping("/api/board/{id}") 
	public ResponseDto<Integer> deleteById(@PathVariable int id) { 
		System.out.println("Board API Controller : Delete 호출!!");
		boardService.deleteBoard(id);
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요
		System.out.println("Board API Controller : Delete 결과!!");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		System.out.println("Board API Controller : Update 호출!!");
		boardService.updateBoard(id,board);
		// 실제로 DB에 insert하고 아래에서 리턴이 되면 되요
		System.out.println("Board API Controller : Update 결과!!");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
}
