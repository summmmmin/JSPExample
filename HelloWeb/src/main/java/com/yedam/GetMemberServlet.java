package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

//조회: 사용자의 입력(searchForm.html)
//처리: 서블릿
//결과: 
@WebServlet("/searchMember")
public class GetMemberServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String empId = req.getParameter("empId");
	
		EmpDAO dao = new EmpDAO();
		Employee result = dao.getEmp(Integer.parseInt(empId));
		if(result!=null) {
			out.print("<table border='1'>");
			out.print("<tr>");
			out.print("<th>사원번호</th>");
			out.print("<td>"+result.getEmployeeId()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>이름</th>");
			out.print("<td>"+result.getFirstName()+", "+result.getLastName()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>email</th>");
			out.print("<td>"+result.getEmail()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>jobid</th>");
			out.print("<td>"+result.getJobId()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>hiredate</th>");
			out.print("<td>"+result.getHireDate()+"</td>");
			out.print("</tr>");
			out.print("</table>");
			out.print("<hr><a href='empList'>목록으로</a>");
		}else {
			resp.sendRedirect("employee/searchForm.html");
		}
		
		
		
	}
}
