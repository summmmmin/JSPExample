<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>

<body>
  <table class="table">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
      </tr>
    </thead>
    <c:forEach var="notice" items="${list }">
      <tr>
        <td><c:out value="${no=no+1 }"></c:out>
        <td><a href="getNotice.do?nid=${notice.noticeId }">${notice.noticeId }</a></td>
        <td>${notice.noticeTitle }</td>
        <td>${notice.noticeWriter }</td>
        <td>${notice.hitCount}</td>
      </tr>
    </c:forEach>
  </table>
</body>

</html>