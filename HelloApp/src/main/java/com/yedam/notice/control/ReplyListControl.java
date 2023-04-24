package com.yedam.notice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class ReplyListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nid = req.getParameter("nid");
		// json데이터생성및 반환
		//[{"replyId":5,"noticeId":98,"reply":"98번댓글",.."replyDate":"2023-05-05"}..]
		ReplyService service = new ReplyServiceImpl();
		String json="[";
		List<ReplyVO> list = service.getReplies(Integer.parseInt(nid));
		for(int i=0; i<list.size(); i++) {
			json += "{\"replyId\":"+list.get(i).getReplyId()+",";
			json += "\"noticeId\":"+list.get(i).getNoticeId()+",";
			json += "\"reply\":\""+list.get(i).getReply()+"\",";
			json += "\"replyWriter\":\""+list.get(i).getReplyWriter()+"\",";
			json += "\"replyDate\":\""+list.get(i).getReplyDate()+"\"}";
			
			if(i+1 != list.size()) {
				json += ",";
			}
		}
		json += "]";
		return json+".json";
	}

}
