<%@page import="com.yedam.domain.Employee"%>
<%@page import="com.yedam.persistence.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
  <%
    // 사원번호 조회시 Employee 정보 가져오는것
//    EmpDAO dao = new EmpDAO();
//  	String empId = request.getParameter("emp_id");
//  	Employee result = dao.getEmp(Integer.parseInt(empId));
	Employee emp = (Employee) request.getAttribute("empInfo");
  %>
  
  <form action="modifyMember.do" method="post">
    <table class="table">
      <tr><th>사원번호</th><td><input name="emp_id" value="<%=emp.getEmployeeId() %>"></td></tr>
      <tr><th>FirstName</th><td><input name="first_name" value="<%=emp.getFirstName() %>"></td></tr>
      <tr><th>LastName</th><td><input name="last_name" value="<%=emp.getLastName() %>"></td></tr>
      <tr><th>Email</th><td><input name="email" value="<%=emp.getEmail() %>"></td></tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="수정">
          <input type="button" value="삭제">
         </td>
       </tr>
    </table>
  </form>
  
  <script>
  // Document Object Model
  	let btn = document.querySelector('input[type="button"]');
  	console.log(btn);
  	btn.onclick = function(){
  		//alert("클릭됨.");
  		let frm = document.querySelector('form');
  		frm.action = "deleteMember.do";
  		frm.submit();	//
  	}
  </script>
<jsp:include page="footer.jsp"></jsp:include>