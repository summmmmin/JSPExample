package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeJsonControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// id, title, subject 받아와서 값을 변경
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.parseInt(req.getParameter("id")) );
		vo.setNoticeTitle(req.getParameter("title") );
		vo.setNoticeSubject(req.getParameter("subject") );
		System.out.println(vo);
		NoticeService service = new NoticeServiceImpl();
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		if(service.modifyNotice(vo)) {
			map.put("retCode", "Success");
			map.put("retVal", service.getNotice(Integer.parseInt(req.getParameter("id"))));
		}else {
			map.put("retCode", "Fail");
			map.put("retVal", "알 수 없는 에러 발생");
		}
		// id를 기준으로 한 건 변경된 값 조회
		
		return gson.toJson(map) + ".json";
	}

}
