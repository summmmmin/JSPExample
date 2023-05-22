// notice.js

function deleteRow(){

  //ajax 호출 id를 기준으로 삭제 후 화면에서 제거
  let tr = $(this).closest('tr');
  let id = tr.children().eq(0).text();

  $.ajax({
    url: 'noticeDelJson.do?nid='+id,
    dataType:'html',
    error: function(xhr){
      console.log(xhr);
    },
    success: function(result){
      if(result == 'Success'){
        console.log(tr);
        tr.remove();
      }else if(result == 'Fail'){
        alert('처리에러')
      }else{
        alert('알 수 없는 에러')
      }
      
    }
    
  })
  .always(function(){
    console.log('final.')
  })

}
// multipart request
$(document).ready(function () {

  // modal 처리, 라이브, 이벤트처리
  $('#noticeList').on('dblclick', 'tr', function(){
    // ajax.nid 활용
    let id = $(this).children().eq(0).text();
    console.log(id);
    $.ajax({
      url: 'getNoticeJson.do?nid='+id,
      dataType: 'json',
      error: function(xhr){
        console.log(xhr);
      },
      success: function(data){
        $('td.nid').text(data.noticeId);
        $('.nTitle').text(data.noticeTitle);
        $('.nWriter').text(data.noticeWriter);
        $('.nSubject').val(data.noticeSubject);
        $('.nAttach').css('width','100px').attr('src', 'images/'+data.attachFile);
      }
    })

    $('#myModal').css('display', 'block');
  })

  $('span.close').on('click', function(){
    $('#myModal').css('display', 'none');
  })

  $(window).on('click', function(e){
    if(e.target == $('#myModal')[0]){
      $('#myModal').css('display', 'none');
    }
  })

  // modal 창에 있는 이미지 클릭
  $('img.nAttach').on('click', function(){
    $('#attachFile').click();   // trigger event
  })

  // 파일 선택 시 change 이벤트
  $('#attachFile').on('change', function(e){
    // 게시글 번호, 파일 => 서버로 전송 : noticeId 기준으로 attach 수정
    console.log(e.target.files[0]);

    let data = new FormData();
    data.append('nid', $('.nid').text());
    data.append('nfile', e.target.files[0]);
    console.log(data);
    $.ajax({
      url: "modifyNoticeFile.do",
      method: "post",
      data: data,
      // multipart요청
      contentType: false,
      processData: false,
      error: function(){
        console.error(err);
      },
      success: function(result){
        console.log(result);
        $('img.nAttach').attr('src','images/'+result.attachFile);
      }
    });
  })

  // 모달창의 수정버튼 클릭
  $('div.modal-body button').on('click', function(e){
    let id = $('div.modal-body td.nid').text();
    let title = $('div.modal-body td.nTitle').text();
    let subject = $('div.modal-body textarea.nSubject').val();
    
    $.ajax({
      url: 'modifyNoticeJson.do',
      method: 'post',
      data: {id: id, title: title, subject: subject},
      error: function(){

      },
      success: function(result){
        if(result.retCode == 'Success'){
          console.log(result.retVal);
          $('#tr_'+result.retVal.noticeId).find('img').attr('src','images/'+result.retVal.attachFile);
          $('#tr_'+result.retVal.noticeId+' td:eq(1)').text(result.retVal.noticeTitle);
          $('#myModal').hide();
          
          console.log($('#tr_'+result.retVal.noticeId+' td:eq(1)').text());
        } else if(result.retCode == 'Fail'){
          alert('error 발생')
        }
      }
    })
  })

  // 등록버튼클릭
  $("form").on("submit", function (e) {
    e.preventDefault(); // form.submit 기능 차단
    var frm = $("form")[0]; //form데이터 담을곳
    $(frm).find('input[name="job"]').val("multi");
    var data = new FormData(frm); // multipart/form-data 처리하는 객체
    for (let val of data.entries()) {
      console.log(val);
    }
    $.ajax({
      url: "addNotice.do",
      method: "post",
      data: data,
      // multipart요청
      contentType: false,
      processData: false,
      error: function (jqxhr) {
        console.log(jqxhr.responseText);
      },
      success: function (data, status, xhr) {
        if(data.retCode == 'Success'){
          let tr = $('<tr />').append( $('<td />').text(data.retVal.noticeId),
                                       $('<td />').text(data.retVal.noticeTitle),
                                       $('<td />').text(data.retVal.noticeWriter),
                                       $('<td />').append($('<img>').css('width', '50px').attr('src','images/'+data.retVal.attachFile)) ,
                                       $('<td />').append($('<button />').text('삭제').on('click',deleteRow)))
          tr.attr('id', 'tr_'+data.retVal.noticeId);
          $('#noticeList').prepend(tr);   
          $('form')[0].reset(); // 폼의 reset 이벤트 호출                       
        }else if(data.retCode == 'Fail'){

        }else{
          alert('알 수 없는 반환값')
        }
        $("form").after(
          $("<p />").text(
            "작성자: " +
              data.retVal.noticeWriter +
              "제목: " +
              data.retVal.noticeTitle +
              "내용: " +
              data.retVal.noticeSubject +
              "파일: " +
              data.retVal.attachFile
          )
        );

      },
    }).always(function () {
      console.log("final");
    });
  });

  // 공지목록출력
  $.ajax({url:'noticeListJson.do',
    method:'GET',
    dataType: 'json',
    error: function(xhr) {
      console.log(xhr.responseText);
    },
    success: function(data){
      console.log(data);
      data.forEach(function(notice){
        let tr = $('<tr />').append( $('<td />').text(notice.noticeId),
                                     $('<td />').text(notice.noticeTitle),
                                     $('<td />').text(notice.noticeWriter),
                                     $('<td />').append($('<img>').css('width', '50px').attr('src','images/'+notice.attachFile)) ,
                                     $('<td />').append($('<button />').text('삭제').on('click',deleteRow)))
        tr.attr('id', 'tr_'+notice.noticeId);
        $('#noticeList').append(tr);
      });
    }
  })
});
