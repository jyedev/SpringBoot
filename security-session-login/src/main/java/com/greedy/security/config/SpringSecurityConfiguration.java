package com.greedy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.security.member.model.service.MemberService;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final MemberService memberService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SpringSecurityConfiguration(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	//암호화에 사용할 객체 BCriptPasswordEncoder bean 등록 - ContextConfiguration
	
	//정적 리소스는 권한이 없어도 요청 가능하게 무시할 경로 작성
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}

	//HTTP 요청에 대한 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	//csrf는 기본적으로 활성화되어 있으므로 비활성화 처리
			//요청에 대한 권한 체크
			.authorizeHttpRequests()
				//요청 보안 수준의 세부적인 설정
				// "/menu/**" 요청은 인증(로그인) 되어야 함을 명시
				.antMatchers("/menu/**").authenticated()
				// "menu/**"의 GET 요청은 member에게 허용
				.antMatchers(HttpMethod.GET, "/menu/**").hasRole("MEMBER")
				// "/menu/**"의 POST 요청은 admin에게 허용
				//hasRole 앞에 ROLE_가 자동으로 붙음
				.antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
				//그 외의 모든 요청은 허가 - 인증(로그인) 되지 않은 사용자도 요청 가능
				.anyRequest().permitAll()
			.and()
				//로그인설정
				.formLogin()
				//로그인 페이지 설정
				.loginPage("/member/login")
				//성공 시 랜딩 페이지 설정
				.successForwardUrl("/")
			.and()
				//로그아웃 설정
				.logout()
				//로그아웃 주소
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				//JSESSIONID 쿠키 삭제
				.deleteCookies("JSESSIONID")
				//세션 만료
				.invalidateHttpSession(true)
				//성공 시 랜딩 페이지
				.logoutSuccessUrl("/")
			.and()
				//인증/인가 예외 처리
				.exceptionHandling()
				//인가되지 않았을 때 - 권한이 없을 때 이동할 페이지
				.accessDeniedPage("/common/denied");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//로그인, 로그아웃은 MemberController에 작성하지 않고 스프링 시큐리티 모듈을 통해 처리
		//사용자 인증을 위해 사용할 MemberService등록, 사용하는 비밀번호 인코딩 방식 설정
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
	}
	

	
}
