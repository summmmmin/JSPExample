<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>공지사항 상세 페이지</h3>
<style>
#content{
	padding:15px auto;}
</style>
<form action="modifyNotice.do" method="get">
  <table class="table">
    <tr>
      <th>글번호</th>
      <td><input type="text" name="nid" value="${noticeInfo.noticeId }" readonly></td>
    </tr>  
    <tr>
      <th>제목</th>
      <td><input type="text" name="title" value="${noticeInfo.noticeTitle }" readonly></td>
    </tr>
    <tr>
      <th>내용</th>
      <td><textarea readonly rows="3" cols="20" name="subject" >${noticeInfo.noticeSubject }</textarea></td>
    </tr>
    <tr>
      <th>작성자</th>
      <td><input type="text" name="writer" value="${noticeInfo.noticeWriter }" readonly></td>
    </tr>
    <tr style="display: none;">
      <th>첨부파일: ${fileType }</th>
      <td>
      <c:if test="${noticeInfo.attachFile != null}">
        <c:choose>
          <c:when test="${fileType == 'image' }">
            <img width="200px" src="images/${noticeInfo.attachFile }">          
          </c:when>
          <c:otherwise>
            <a href="images/${noticeInfo.attachFile }">${noticeInfo.attachFile }</a>
          </c:otherwise>
        </c:choose>
      </c:if>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">      
	    <c:if test="${id == noticeInfo.noticeWriter || id=='admin@email.com'}">
        <button type="submit">수정</button>      
        </c:if>        
        <button type="button" onclick="location.href='noticeList.do?page=${pageNum}'">목록</button>
      </td>
    </tr>
  </table>
</form>

<!-- 댓글등록 -->
<div id="content">
  <input type="text" id="reply">
  <span>${id }</span>
  <button type="button" id="addBtn">등록</button>
</div>

<!-- 댓글정보 -->
<table class="table">
  <thead>
    <tr>
      <th>댓글번호</th><th>글내용</th><th>작성자</th>
    </tr>
  </thead>
  <tbody id="tlist">
    <!-- <tr>
           <td>번호</td><td>글내용</td><td>작성자</td>
         </tr> -->
  </tbody>
</table>


<script>
  let showFields = ['replyId', 'reply', 'replyWriter']
  let xhtp = new XMLHttpRequest();	// Ajax 호출
  xhtp.open('get', 'replyList.do?nid=${noticeInfo.noticeId}');
  xhtp.send();
  xhtp.onload = function(){
	  console.log(xhtp.response);
	  let data = JSON.parse(xhtp.response);
	  let tlist = document.querySelector('#tlist');
	  for(let reply of data){
		  let tr = makeTrFunc(reply);
		  tlist.append(tr);
	  }
  }

  // tr생성하는 함수
  function makeTrFunc(reply={}){
    let tr = document.createElement('tr');
		for(let prop of showFields){
		  let td = document.createElement('td');
		  td.innerText = reply[prop];
		  tr.append(td);			  
	  }
    //삭제버튼
    let btn = document.createElement('button');
    btn.addEventListener('click', function(e){
      let delNo = btn.parentElement.parentElement.children[0].innerText;
      // db에서 삭제 후 화면에서 삭제
      let xhtp = new XMLHttpRequest();
      xhtp.open('post', 'removeReply.do')
      xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
      xhtp.send('rid='+delNo);
      
      xhtp.onload = function(){
        let result = xhtp.response;
        if(result.retCode == 'Success'){
          //화면에서 지우기
          let tr = makeTrFunc(result.data);
          tlist.remove(tr);
        }else if(result.retCode == 'Fail'){
          alert('처리 중 에러 발생')
        }else{
          alert('알 수 없는 결과')
        }
      }
    })
    btn.innerText = '삭제'
    let td = document.createElement('td');
    td.append(btn);
    tr.append(td);

    return tr;  //생성한 tr 반환
  }
  
  // 등록이벤트...
  document.querySelector("#addBtn").addEventListener('click', addReplyFnc);
  function addReplyFnc(e){
	  //로그인시댓글작성가능
	  
	  
	  console.log('click', e.target);	//버튼
	  console.log('reply', document.querySelector("#reply").value);
	  console.log('id', "${id}");
	  let reply = document.querySelector("#reply").value;
	  
	  //  Ajax 호출
	  let xhtp = new XMLHttpRequest();
	  xhtp.open('post', 'addReply.do');
	  xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	  xhtp.send('id=${id}&reply='+reply+'&notice_id=${noticeInfo.noticeId}');	//get방식은 넘어가는 데이터가 url에 들어가지만 post는 send에서 키 밸류 형식으로 넘김
	  xhtp.onload = function(){
		  console.log(xhtp.response);
      let result = JSON.parse(xhtp.response);
      if(result.retCode == 'Success'){
        // 값을 활용해서 tr생성
        let tr = makeTrFunc(result.data);
        tlist.append(tr)
      } else if(result.retCode == 'Fail'){
        alert('처리중 에러')
      }
	  }
  }
</script>




