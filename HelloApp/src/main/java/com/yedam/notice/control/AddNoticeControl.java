package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class AddNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 파일업로드/ db입력처리/ 목록으로 이동.
		// 멀티파트요청: 요청정보, 저장경로, 최대파일사이즈, 인코딩, 리네임정책인스턴스.
		String saveDir = req.getServletContext().getRealPath("images");
		int maxSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy rn = new DefaultFileRenamePolicy();
		MultipartRequest multi
		=new MultipartRequest(req, saveDir, maxSize, encoding, rn);
		
		String writer = multi.getParameter("writer");
		String subject = multi.getParameter("subject");
		String title = multi.getParameter("title");			//등록시 입력값
		String attach = multi.getFilesystemName("attach");	//파일 등록시 바뀐이름저장
		
		// 사용자 입력값을 NoticeVO에 입력
		NoticeVO vo = new NoticeVO();
		vo.setAttachFile(attach);
		vo.setNoticeSubject(subject);
		vo.setNoticeTitle(title);
		vo.setNoticeWriter(writer);
		
		NoticeService service = new NoticeServiceImpl();
		if(service.addNotice(vo)) {
			return "noticeList.do";	//정상처리->목록이동
		} else {
			return "main.do";	//정상처리->main이동
		}
	}

}
