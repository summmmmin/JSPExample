package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		String job = req.getParameter("job");
		job = job==null ? "multi" : "ajax";
		if(job.equals("ajax")) {
			String title = req.getParameter("title");
			String subject = req.getParameter("subject");
			String writer = req.getParameter("writer");
			String attach = req.getParameter("attach");		// input:file인 경우.
			
			NoticeVO vo = new NoticeVO();
			vo.setAttachFile(attach);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			
			NoticeService service = new NoticeServiceImpl();
			// map => {retCode:Success, retVal: vo}
			// map => {retCode:Fail, retVal:null}
			Map<String, Object> map = new HashMap<>();
			
			Gson gson = new GsonBuilder().create();
			if(service.addNotice(vo)) {
				map.put("retCode", "Success");
				map.put("retVal", vo);
//				return "Success.json";	//정상처리->목록이동
			} else {
				map.put("retCode", "Fail");
				map.put("retVal", "알 수 없는 에러 발생");
//				return "Fail.json";	//정상처리->main이동
			}
			return gson.toJson(map) + ".json";	// 객체 => json문자열
		} else{
			
		
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
		
		
//		if(service.addNotice(vo)) {
//			
//			return "noticeList.do";	//정상처리->목록이동
//		} else {
//			
//			return "main.do";	//정상처리->main이동
//		}
		
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		if(service.addNotice(vo)) {
			map.put("retCode", "Success");
			map.put("retVal", vo);
//			return "Success.json";	//정상처리->목록이동
		} else {
			map.put("retCode", "Fail");
			map.put("retVal", "알 수 없는 에러 발생");
//			return "Fail.json";	//정상처리->main이동
		}
		return gson.toJson(map) + ".json";	// 객체 => json문자열
		} //end of job().
	}	//end of method().

}// end of class.
