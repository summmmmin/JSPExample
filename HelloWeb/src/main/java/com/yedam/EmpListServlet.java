package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/empList")
public class EmpListServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
			
		out.print("<table border='1'>");
		out.print("<thread><tr><th>사원번호</th><th>FirstName</th><th>LastName</th><th>email</th><th>JobId</th></tr></thread>");
		out.print("<tbody>");
		//while(rs.next()) {
		for(Employee emp : list) {
//			System.out.println("eid: "+rs.getInt("employee_id")+",fName: "+rs.getString("first_name"));
			out.print("<tr><td><a href='searchMember?empId="+emp.getEmployeeId()+"'>"+emp.getEmployeeId()+"</a></td><td>"+emp.getFirstName()
			+"</td><td>"+emp.getLastName()+"</td><td>"+emp.getEmail()
			+"</td><td>"+emp.getJobId()+"</td></tr>");			
		}
		//}
		out.print("</tbody>");
		out.print("</table>");
			
	}
	
	public static void main(String[] args) {

	}
}
