package com.greedy.security.member.model.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserImpl extends User {


	private int no;						//회원번호
	private String id;					//회원아이디
	private String pwd;					//회원비밀번호
	private String tempPwdYn;			//임시비밀번호여부
	private Date pwdChangedDateTime;	//회원비밀번호변경일자
	private String pwdExpDate;			//회원비밀번호만료일자
	private String name;				//회원이름
	private Date registDateTime;		//회원가입일시
	private int accumLoginCount;		//누적로그인횟수
	private int loginFailedCount;		//로그인연속실패횟수
	private String accLockYn;			//계정잠금여부
	private String accInactiveYn;		//계정비활성화여부
	private String accExpDate;			//계정만료일자
	private String accExpYn;			//계정만료여부
	private Date accSecessionDateTime;	//계정탈퇴일시
	private String accSecessionYn;		//계정탈퇴여부
	
	/* TBL_MEMBER_ROLE - 한 멤버는 여러 권한을 가질 수 있다. */
	private List<MemberRoleDTO> memberRoleList;	//권한목록
	
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(MemberDTO member) {
		this.no = member.getNo();
		this.id = member.getId();
		this.pwd = member.getPwd();
		this.tempPwdYn = member.getTempPwdYn();
		this.pwdChangedDateTime = member.getPwdChangedDateTime();
		this.pwdExpDate = member.getPwdExpDate();
		this.name = member.getName();
		this.registDateTime = member.getRegistDateTime();
		this.accumLoginCount = member.getAccumLoginCount();
		this.loginFailedCount = member.getLoginFailedCount();
		this.accLockYn = member.getAccLockYn();
		this.accExpDate = member.getAccExpDate();
		this.accSecessionDateTime = member.getAccSecessionDateTime();
		this.accSecessionYn = member.getAccSecessionYn();
		this.memberRoleList = member.getMemberRoleList();
	}
	
}
