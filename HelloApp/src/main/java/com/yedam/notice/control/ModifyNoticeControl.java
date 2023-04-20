package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService service = new NoticeServiceImpl();
		if (req.getMethod().equals("GET")) {
			String nid = req.getParameter("nid");
			
			NoticeVO vo = service.getNotice(Integer.parseInt(nid));
			req.setAttribute("noticeInfo", vo);
			
			return ("notice/noticeModify.tiles");
		}else if (req.getMethod().equals("POST")) {
			String nid = req.getParameter("nid");
			String title = req.getParameter("title");	
			String subject = req.getParameter("subject");
			NoticeVO vo = new NoticeVO();
			vo.setNoticeId(Integer.parseInt(nid));
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			service.modifyNotice(vo);
		}
		return "noticeList.do";
		
	}

}
