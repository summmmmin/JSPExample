<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="my" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <jsp:include page="../main.jsp"></jsp:include>
  <my:if test="${list != null }">
    <p>${list }</p>
  </my:if>
  
  <my:forEach begin="1" end="10" step="2" var="i">
    <p>${i }</p>
  </my:forEach>
  
  <my:forEach var="notice" items="${list }">
    <p>${notice }</p>
  </my:forEach>
</body>
</html>