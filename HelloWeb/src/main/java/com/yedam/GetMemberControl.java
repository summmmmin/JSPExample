package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class GetMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//매개값으로 사원번호
		String empId = req.getParameter("emp_id");
		EmpDAO dao = new EmpDAO();
		Employee emp = dao.getEmp(Integer.parseInt(empId));
		req.setAttribute("empInfo", emp);	//값을 getMember.jsp에서 getAttribute로 받음
		//페이지 재지정. control -> getMember.jsp
		try {
			req.getRequestDispatcher("WEB-INF/views/getMember.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
