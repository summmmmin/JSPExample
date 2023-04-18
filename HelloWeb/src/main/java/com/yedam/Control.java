package com.yedam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Control {

	public void exec(HttpServletRequest req, HttpServletResponse resp);
}
