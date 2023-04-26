package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.member.domain.EventVO;
import com.yedam.member.service.EventService;
import com.yedam.member.service.EventServiceImpl;

public class RemoveEventControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EventVO event = new EventVO();
		EventService service = new EventServiceImpl();
		boolean result = service.removeEvent(event);
		
		String json = "";
		
		if(result) {
			// {"retCode":"Success"}
			json = "{\"retCode\":\"Success\"}";
			
		}else {
			// {"retCode":"Fail"}
			json = "{\"retCode\":\"Fail\"}";
		}	
		return json + ".json";
	}

}
