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

<table style="display: none;">
  <tbody>
    <tr class="template">
      <td>10</td>
      <td><input type="text" class="reply"></td>
      <td>user01</td>
      <td><button>수정</button></td>
    </tr>
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
	let check = 0;
  // tr생성하는 함수
  function makeTrFunc(reply={}){
    let tr = document.createElement('tr');
    tr.id = reply.replyId;	//tr에 속성추가:댓글번호
    
    // this 
    // 1)object안에서 사용되면 그 object자체를 가리킴
    //   let obj={name:"hong", age:20, showInfo:function(){this.age + this.name}}
    // 2)fuction 선언 안에서 this는 window 전역객체. <->지역객체
    //	 function add(){console.log(this)}
    // 3)event 안에서 사용되는 this는 이벤트를 받는 대상
    //	 document.getElementById('tlist').addEventListener('click', function(){console.log(this)})
    
    // tr 클릭이벤트
    tr.addEventListener('dblclick', function(e){
    	console.log(this.id);	//tr
    	
    	if(this.children[2].innerText != '${id}'){	//작성자가 아닐경우 수정불가
    		return
    	}
    	
    	let template = document.querySelector('.template').cloneNode(true);		// template 복제
    	console.log(template);
    	//template.children[0].innerText = reply.replyId;
    	//template.children[1].children[0].value = reply.reply;
    	//template.children[2].innerText = reply.replyWriter;
    	template.querySelector('td:nth-of-type(1)').innerText = reply.replyId;	
    	template.querySelector('td:nth-of-type(2)>input').value = reply.reply;
    	template.querySelector('td:nth-of-type(3)').innerText = reply.replyWriter;
    	template.querySelector('button').addEventListener('click', function(e){
    		// Ajax 호출
    		let replyId = reply.replyId;
    		let replyCon = this.parentElement.parentElement.children[1].children[0].value;
    		console.log(replyId, replyCon);
    		
    		let xhtp = new XMLHttpRequest();
    		xhtp.open('post', 'modifyReply.do');
    		xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    		xhtp.send('rid='+replyId+'&reply='+replyCon);
    		xhtp.onload = function(){
    			let result = JSON.parse(xhtp.response);
    			if(result.retCode == 'Success'){
    				// 화면변경
    				alert('수정완료')
    				let tr = makeTrFunc(result.data);
    			//	oldtr = document.querySelector('.template')
    				tlist.replaceChild(tr,template);
    			} else if(result.retCode =='Fail'){
    				alert('처리 중 에러')
    			} else{
    				alert('알 수 없는 반환값')
    			}
    		}
    	})
    	//화면전환
    	document.getElementById('tlist').replaceChild(template, tr);

    })
    //td생성
    for(let prop of showFields){
      let td = document.createElement('td');
      td.innerText = reply[prop];
      tr.append(td);	
      if(reply['replyWriter'] == '${id}'){
		check = 1
      }else{
    	  check = 0
      }
	}
    //삭제버튼
    let btn = document.createElement('button');
    btn.addEventListener('click', function(e){
      let writer = btn.parentElement.previousElementSibling.innerText;	//버튼의 부모요소의 형제=작성자
      console.log(writer, '${id}')	//작성자, 로그인아이디
      if(writer != '${id}'){
    	  alert('권한이 없습니다.')
    	  return;
      }
      console.log(btn.parentElement.parentElement);	
      let delNo = btn.parentElement.parentElement.id;	//tr의 id속성을 삭제번호로
      // db에서 삭제 후 화면에서 삭제
      let xhtp = new XMLHttpRequest();
      xhtp.open('post', 'removeReply.do')
      xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
      xhtp.send('rid='+delNo);	//요청방식이 post일 때 parameter를 send()에 포함
      console.log(delNo);
      xhtp.onload = function(){
        let result = JSON.parse(xhtp.response);
        if(result.retCode == 'Success'){
          //화면에서 지우기
          alert('삭제완료')
          btn.parentElement.parentElement.remove();
        }else if(result.retCode == 'Fail'){
          alert('처리 중 에러 발생')
        }else{
          alert('알 수 없는 결과')
        }
      }
    })

    btn.innerText = '삭제'
    let td = document.createElement('td');
	if(check == 1){
    td.append(btn);
    tr.append(td);
		
	}

    return tr;  //생성한 tr 반환
  }
  
  // 등록이벤트...
  document.querySelector("#addBtn").addEventListener('click', addReplyFnc);
  function addReplyFnc(e){
	  //로그인시댓글작성가능
	  let id = document.querySelector('#content span').innerText;
	  if(id == null || id ==''){
		  alert('로그인하세요')
		  location.href = 'loginForm.do'
		  return
	  }
	  
	  console.log('click', e.target);	//버튼
	  console.log('reply', document.querySelector("#reply").value);
	  console.log('id', "${id}");
	  let reply = document.querySelector("#reply").value;
	  if(reply == null || reply == ''){
		  alert('입력하세요')
		  return
	  }

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
        
        document.getElementById("reply").value='';
        document.getElementById("reply").focus();
      } else if(result.retCode == 'Fail'){
        alert('처리중 에러')
      }
	  }
  }
</script>




