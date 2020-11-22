package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {
	    
	   @Autowired // 의존성 주입(DI)
	    private UserRepository userRepository;
	   
	   @DeleteMapping("/dummy/user/{id}")
	    public String delete(@PathVariable int id) {
		   try {
			   userRepository.deleteById(id);
		  // Exception : 최상위 예외
		   }catch(EmptyResultDataAccessException e) {
			   return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		   }
		   
		   
		   return  "삭제되었습니다. id " + id;
	   }
	   
	   //email, password
	   @Transactional //함수 종료시에 자동 commit됨
	   @PutMapping("/dummy/user/{id}")
	   // json데이터를 요청 -> java object(message converter의 jacson라이브러리가 변환해서 받아줘요) @RequestBody
	    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		   System.out.println("출력로그"+ id +","+ requestUser.getPassword()+","+requestUser.getEmail());
		   
		   // 람다식 방식
		   User user = userRepository.findById(id).orElseThrow(()->{
			   return new IllegalArgumentException("수정에 실패하였습니다.");
		   }); 
		   
			user.setPassword(requestUser.getPassword());
			user.setEmail(requestUser.getEmail());
		   // save 함수 용도 
			// pk를 전달하지 않으면 Insert, 있으면 update
		   //userRepository.save(user);
		  // @Transactional -> save하지않아도 update된다. 더티 체킹
		   return user;
	   }
	   
	   
	   @GetMapping("/dummy/users")
	   public List<User> list(){
		   return userRepository.findAll();
	   }
	   
	   //한페이지당 2건에 데이터를 리턴받아 볼 예정
	   @GetMapping("/dummy/user")
	   public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		   Page<User> pagingUser = userRepository.findAll(pageable);
		   List<User> users = pagingUser.getContent();
		   return users;
	   }
	   
	   // {id} 주소로 파라메터 전달 받을 수 있음
	   @GetMapping("/dummy/user/{id}")
	   public User detail(@PathVariable int id) {
		   // user4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것아나 ?
		   //그럼 return null이 리턴되자나, 그럼 문제겠지
		   // Optional로 너의 User객체를 가져올테니, null인지 판단해서 return해
		   User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			   @Override
			   public IllegalArgumentException get() {
				   return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			   }
		   });
		   
		   // 요청 : 웹브라우저
		   // User객체 = 자바 오브젝트
		   // 변환 (웹프라우저가 이해할 수 있는 데이터) -> Json
		   // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		   // 만약 자바오프젝트를 리턴하게 되면 messageconverter가 jacson라이브러리 호출해서
		   //User오프젝트를 json으로 변환해서 브라우저에 전송
		   return user;
	   }
	   
		@PostMapping("/dummy/join")
        public String join(User user) { //object로 수신
			System.out.println("ID :"+ user.getId());
			System.out.println("username:"+ user.getUsername());
			System.out.println("Password :"+ user.getPassword());
			System.out.println("email :"+ user.getEmail());
			System.out.println("Role :"+ user.getRole());
			System.out.println("Date :"+ user.getCreateDate());
			
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return "회원가입이 완료되었습니다.";
		}
}
