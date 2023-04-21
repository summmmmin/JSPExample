<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>회원정보 수정 페이지</h3>
<form action="modifyMember.do" method="post">
  <table class="table">
    <tr>
      <th>email</th>
      <td><input type="text" name="email" value="${memberInfo.email }" readonly></td>
    </tr>  
    <tr>
      <th>password</th>
      <td><input type="text" name="pw" value="${memberInfo.password }"></td>
    </tr>
    <tr>
      <th>name</th>
      <td><input type="text" name="name" value="${memberInfo.name }" readonly></td>
    </tr>
    <tr>
      <th>phone</th>
      <td><input type="text" name="phone" value="${memberInfo.phone }"></td>
    </tr>
    <tr>
      <th>address</th>
      <td><input type="text" name="addr" value="${memberInfo.address }"></td>
    </tr>
    
    
    <tr>
      <td colspan="2" align="center">
        <button type="submit">저장</button>
      </td>
    </tr>
  </table>
</form>