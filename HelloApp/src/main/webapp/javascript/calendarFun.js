let mon = 5;
let days = ["일", "월", "화", "수", "목", "금", "토"];
let tbl = document.createElement("table");
let tr = document.createElement("tr");
let th = document.createElement("th");
let td = document.createElement("td");
let head = document.createElement("thead");
let body = document.createElement("tbody");
function makeCalendar(month = 1) {
  //table생성
  //날짜
  for (let i of days) {
    th = document.createElement("th");
    th.innerText = i;
    tr.append(th);
  }
  head.append(tr);
  tbl.append(head);
  tbl.border = "1";
  tr = document.createElement("tr");

  //첫째날 위치지정
  for (let i = 0; i < getFirstDay(month); i++) {
    tr.append(document.createElement("td"));
  }

  for (let i = 1; i <= getLastDate(month); i++) {
    // Dom 활용해서 달력생성
    td = document.createElement("td");
    td.innerText = i;
    tr.append(td);
    if ((i + getFirstDay(month)) % 7 == 0) {
      body.append(tr);
      tr = document.createElement("tr");
    }

    //색깔지정
    if ((i + getFirstDay(mon)) % 7 == 0) {
      td.style.backgroundColor = "green";
      td.style.color = "white";
    } else if ((i + getFirstDay(mon)) % 7 == 1) {
      td.style.backgroundColor = "blue";
      td.style.color = "white";
    }
  }
  body.append(tr);
  tbl.append(body);
  //table끝
  return tbl;
}

document.querySelector("#show").append(makeCalendar(5));

//월정보를 받고 첫번째 날짜의 값을 반환해주는 함수
function getFirstDay(month) {
  if (month == 5) {
    return 1;
  } else if (month == 6) {
    return 4;
  }
}
//월정보를 받고 마지막 날짜 반환해주는 함수
function getLastDate(month = 5) {
  if (month == 5) {
    return 31;
  } else if (month == 6) {
    return 30;
  }
}
