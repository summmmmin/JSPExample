package com.yedam.domain;

import java.util.List;

import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class SampleExe3 {

	public static void main(String[] args) {
		
		ReplyService service = new ReplyServiceImpl();
		service.getReplies(100);
		List<ReplyVO> list = service.getReplies(98);
		
		for(ReplyVO vo : list) {
			System.out.println(vo);
		}
	}

}
