package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class RemoveReplyControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("rid");
		
		ReplyService service = new ReplyServiceImpl();
		boolean result = service.removeReply(Integer.parseInt(id));
		
		String json = "";
		Map<String, Object> map = new HashMap<>();
		
		if(result) {
			// {"retCode":"Success"}
//			json = "{\"retCode\":\"Success\"}";
			// {"retCode":"Success", "data":vo}
			map.put("retCode", "Success");
			map.put("data", id);
			
		}else {
			// {"retCode":"Fail"}
//			json = "{\"retCode\":\"Fail\"}";
			map.put("retCode", "Fail");
		}
		
		Gson gson = new GsonBuilder().create();  //gson 객체
		json = gson.toJson(map);
		
		return json + ".json";
	}

}
