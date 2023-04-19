<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <p>Main Page</p>
  <p>Expression Language</p>
  ${3>2}
  ${"Hello" }
  ${10 - 5 }
  ${myName != null}  <!-- setAttribute값 -->
  ${myName != null ? '<p>있음</p>' : '<p>없음</p>' }
</body>
</html>