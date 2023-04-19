package com.yedam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.invalidate();    // 세션객체의 정보 삭제
		
		try {
			resp.sendRedirect("loginForm.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
