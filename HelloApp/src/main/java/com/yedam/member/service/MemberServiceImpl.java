package com.yedam.member.service;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService{

	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return mapper.loginCheck(vo);
	}
	
	@Override
	public boolean modifyMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateMember(vo) == 1;	//들어가면 true
	}
	
	@Override
	public MemberVO getMember(String email) {
		// TODO Auto-generated method stub
		return mapper.getMember(email);
	}
}
