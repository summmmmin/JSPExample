// basic.js

// document.addEventListener('DOMContentLoaded', function(){
//   let liTag = document.createElement('li');  //Document Object Model
//   liTag.innerText = 'Cherry';
//   liTag.append()
//   console.log(liTag)
//   document.querySelector('#list').append(liTag);
// })

// 자바스크립트 객체 vs. jQuery 객체
$(document).ready(function () {
  let elem = jQuery("<li />");
  // let elem = $('<li />'); : jQuery 대신 $ 로 줄여쓸수있음
  // elem.innerText : 에러 -> jQuery 제공 함수를 써야함
  elem.text("Cherry");

  //console.log(elem);

  //$('#list').append(elem);
  $("#list").append($("<li />").text("Cherry"), $("<li />").text("Mango"));
});
