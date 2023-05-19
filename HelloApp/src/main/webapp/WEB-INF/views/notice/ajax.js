// $.ajax() 사용
$(document).ready(function () {
  $("form").on("submit", function (e) {
    e.preventDefault(); // 전송차단

    $.ajax({
      url: "addNotice.do",
      data: $(this).serialize(), //id=12&name=hong&...
      method: "post",
      dataType: "json",
      error: function (jqxhr, textStatus, error) {
        console.log(jqxhr, textStatus, error);
      },
      success: function (data, textStatus, jqXHR) {
        console.log(data, textStatus, jqXHR);

        let ul = $("<ul />").append(
          $("<li />").text("작성자: " + data.retVal.noticeWriter),
          $("<li />").text("제목 : " + data.retVal.noticeTitle),
          $("<li />").text("내용 : " + data.retVal.noticeSubject)
        );
        $("form").after(ul);
      },
    }).always(function () {
      console.log("final.");
    });
  });
});
