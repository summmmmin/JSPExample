package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;
import com.yedam.notice.domain.NoticeVO;

public class ModifyMemberControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberServiceImpl();
		if (req.getMethod().equals("GET")) {
			String email = req.getParameter("email");
			
			MemberVO vo = service.getMember(email);
			req.setAttribute("memberInfo", vo);
			
			return ("member/memberModify.tiles");
		}else if (req.getMethod().equals("POST")) {
			String email = req.getParameter("email");
			String pw = req.getParameter("pw");	
			String phone = req.getParameter("phone");
			String addr = req.getParameter("addr");
			MemberVO vo = new MemberVO();
			vo.setEmail(email);
			vo.setPassword(pw);
			vo.setPhone(phone);
			vo.setAddress(addr);
			service.modifyMember(vo);
		}
		return "noticeList.do";		
		
	}

}
