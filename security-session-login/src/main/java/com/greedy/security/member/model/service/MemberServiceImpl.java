package com.greedy.security.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.security.member.model.dao.MemberMapper;
import com.greedy.security.member.model.dto.AuthorityDTO;
import com.greedy.security.member.model.dto.MemberDTO;
import com.greedy.security.member.model.dto.MemberRoleDTO;
import com.greedy.security.member.model.dto.UserImpl;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	
	//사용자 아이디를 통해 사용자 정보 조회하는 기능 - 로그인 시 호출 될 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//우리가 정의한 타입으로 유저 조회
		MemberDTO member = memberMapper.findMemberById(username);
		
		//null값이 없게 하기 위해 조회 된 값이 없을 시 빈 객체
		if(member == null) member = new MemberDTO();
		
		//권한 리스트
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(member != null && member.getMemberRoleList() != null) {
			
			for(MemberRoleDTO role : member.getMemberRoleList()) {
				AuthorityDTO authority = role.getAuthority();
				
				if(authority != null) {
					authorities.add(new SimpleGrantedAuthority(authority.getName()));
				}
			}
		}
		
//		return new User(member.getId(), member.getPwd(), authorities);
		
		//User객체에 담기지 않는 추가 정보를 User 객체를 extends한 UserImpl에 담아서 리턴한다.
		UserImpl user = new UserImpl(member.getId(), member.getPwd(), authorities);
		user.setDetails(member);
		
		return user;
	}

}
