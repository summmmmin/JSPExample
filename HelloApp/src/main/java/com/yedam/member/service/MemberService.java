package com.yedam.member.service;

import com.yedam.member.domain.MemberVO;

public interface MemberService {

	public MemberVO loginCheck(MemberVO vo);
	public MemberVO getMember(String email);
	public boolean modifyMember(MemberVO vo);
}
