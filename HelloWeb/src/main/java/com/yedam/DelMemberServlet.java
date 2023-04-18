package com.yedam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/delMemberServlet")
public class DelMemberServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String empId = req.getParameter("empId");
		EmpDAO dao = new EmpDAO();
		boolean result = dao.deleteEmployee(Integer.parseInt(empId));
		if(result) {
			resp.sendRedirect("empList");
		}else {
			resp.sendRedirect("employee/delForm.html");
		}
		
		
//		// jdbc 연결 접속 조회
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
//			// 쿼리 조회 -> 처리결과 : preparedstatement
//			String sql = "delete from employees\r\n"
//					+ "where employee_id = ?";
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, empId);
//			
//			int r = psmt.executeUpdate();
//			System.out.println("처리된 건수: "+r);
//			resp.sendRedirect("empList"); 
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("emp_id: "+empId);
	}
}
