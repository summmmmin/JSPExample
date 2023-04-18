package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/modifyMemberServlet")
public class ModifyMemberServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empId = req.getParameter("emp_id");
		String fname = req.getParameter("first_name"); 
		String lname = req.getParameter("last_name");
		String email = req.getParameter("email");
		
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.parseInt(empId));
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		
		EmpDAO dao = new EmpDAO();
		boolean result = dao.updateMember(emp);
		
		if(result) {
			resp.sendRedirect("empList");
		}else {
			resp.sendRedirect("modifyMember.jsp");
		}
	}
}
