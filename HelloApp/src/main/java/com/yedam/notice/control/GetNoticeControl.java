package com.yedam.notice.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class GetNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// param: nid
		
		String nid = req.getParameter("nid");
		
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = service.getNotice(Integer.parseInt(nid));
		req.setAttribute("noticeInfo", vo);
		
		if(vo.getAttachFile()!=null) {
			String imgPath = req.getServletContext().getRealPath("images");
			Path file = Paths.get(imgPath + "/" + vo.getAttachFile());
			System.out.println(Files.probeContentType(file));
			// image/jpg, image/png, text/plain, 
			String fileType = Files.probeContentType(file);
			req.setAttribute("fileType", fileType.substring(0, fileType.indexOf("/")));
		}
		
		return "notice/noticeGet.tiles";
	}

}
