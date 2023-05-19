// fetchMember.js

$(function () {
  $("#list").remove();
  $("#show2").remove();
  $("#show").empty();
  $("#show").before("<h3>회원목록</h3>");
  $("#show3").remove();

  // json 데이터 출력
  fetch("MOCK_DATA.json")
    .then(function (resolve) {
      return resolve.json(); // stream -> object 변경
    })
    .then(makeList)
    .catch(function () {
      console.error(err);
    }); // end of fetch()

  // 등록버튼 이벤트 추가
  $("button:contains(등록)").on("click", addMemberFnc);
  function addMemberFnc() {
    if($("#id").val() == "" || $("#fname").val() == "" || $("#lname").val() == ""){
      alert('입력하세요')
    }else{
      let tr = $("<tr />").append(
        $("<td />").text($("#id").val()),
        $("<td />").text($("#fname").val()),
        $("<td />").text($("#lname").val()),
        $("<td />").text($("#gender").val()),
        //버튼작성
        $("<td />").append($("<button>삭제</button>").on("click", delMember))
      );
      $("#memberList").append(tr)
    }
    // 입력값 지워주기
    $('input').val('')
  }

  // 변경버튼이벤트 추가
  $("button:contains(변경)").on("click", modifyMemberFnc);
  function modifyMemberFnc(){
    let id = $("#id").val()
    console.log($('#memberList tr:eq(0) td:eq(0)').text() == id)
    
  }
  // 라이브이벤트
  $('body').on('dblclick','#memberList tr', function(){
    console.log('클릭')
    let oldTr = $(this);
    let oldId = $(this).children().eq(0).text();
    let oldFname = $(this).children().eq(1).text();
    let oldLname = $(this).children().eq(2).text();
    let oldGender = $(this).children().eq(3).text();
    // 새로운 tr 생성
    let newTr = $('<tr />').append(
      $('<td />').text(oldId),
      $('<td />').append($('<input />').val(oldFname)),
      $('<td />').html('<input type="text" value="'+oldLname+'">'),
      $('<td />').html(oldGender),
      $('<td />').append($('<button />').text('수정').on('click', updateTr))
    );
    console.log(oldTr);
    oldTr.replaceWith(newTr);
  })  

  function updateTr(){
    let oldTr = $(this).parentsUntil('tbody');

    let id = oldTr.find('td:eq(0)').text();
    let fname = oldTr.find('td:eq(1)>input').val();
    let lname = oldTr.find('td:eq(2)>input').val();
    let gender = oldTr.find('td:eq(3)').text();
    
    let newTr = $('.template').clone();
    newTr.find('td:eq(0)').text(id);
    newTr.find('td:eq(1)').text(fname);
    newTr.find('td:eq(2)').text(lname);
    newTr.find('td:eq(3)').text(gender);
    newTr.find('button').on("click", delMember);
    newTr.removeClass('template');
    oldTr.replaceWith(newTr);
  }

  function makeHead() {
    // title 등록
    $("table:eq(1)").prepend(
      $("<thead />").append(
        $("<th />").text("ID"),
        $("<th />").text("이름"),
        $("<th />").text("성씨"),
        $("<th />").text("성별"),
        $("<th />").text("삭제"),
        $("<th />").html('<input type="checkbox">').on('click', checkall)
      )
    );
  }

  // 한 라인 삭제
  function delMember(e) {
    console.log(e.target);
    $(e.target).parentsUntil("tbody").remove();
  }

  function makeList(result) {
    console.log(result);
    let tbl = $('<table border="1" />');
    let tbd = $("<tbody />").attr("id", "memberList");
    result.forEach(function (member, idx) {
      if (idx < 5) {
        let tr = $("<tr />").append(
          $("<td />").text(member.id),
          $("<td />").text(member.first_name),
          $("<td />").text(member.last_name),
          $("<td />").text(member.gender),
          //버튼작성
          $("<td />").append($("<button>삭제</button>").on("click", delMember)),
          //체크박스
          $('<td />').append($('<input type="checkbox">'))
        )
        tbd.append(tr);
      }
    });
    tbl.append(tbd);
    $("#show").append(tbl); // <div id="show"><table border='1'>...</table></div>
    makeHead();
  }

  function checkall(e){
    if($(e.target).prop("checked")){
      $('#memberList input').prop("checked", true)
    }else{
      $('#memberList input').prop("checked", false)
    }
  }

  $("button:contains(선택된tr이동)").on("click", trMove);
  function trMove(){
    console.log($('#memberList tr'))
  }
});
