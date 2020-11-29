package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록의 의미는 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration //빈등록(IOC관리)
@EnableWebSecurity //Security 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled=true) // 특정주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC가 되요!!, 스프링이 관리해요
		public BCryptPasswordEncoder encodePWD() {
			return new BCryptPasswordEncoder();
		}
	
	// 시큐리티가 대신 로그인해주는데 패스워드를 가로채기를 하는데
	// 해당 패스워드가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		System.out.println("암호비교");
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
      protected void configure(HttpSecurity http) throws Exception{
    	  http
    	    .csrf().disable() //csrf 토큰 비활성화(테스트시 걸어주는 게 좋음
    	  	.authorizeRequests()
    	  		.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**") // 해당하지 않은 Url에 대한 요청
    	  		.permitAll()
    	  		.anyRequest()
    	  		.authenticated() //인징이 되지 않은 경우
    	  .and()
    	  		.formLogin()
    	  		.loginPage("/auth/loginForm") // 해당 url로 이동
    	        .loginProcessingUrl("/auth/loginProc") //해당 url요청시 스프링 시큐리티가 대신 처리
    	        .defaultSuccessUrl("/"); // 정상적으로 요청이 완료되면 해당 url로 포워드
      }

	private HttpSecurity and() {
		// TODO Auto-generated method stub
		return null;
	}
}
