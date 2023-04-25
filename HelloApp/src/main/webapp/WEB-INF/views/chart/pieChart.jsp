<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {
      packages: ["corechart"]
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
      console.log('1') //순서확인
      var result = [
        ['dept', 'cnt']
      ];
      console.log('2')
      let xhtp = new XMLHttpRequest(); //비동기방식처리. 순차적이지않음
      xhtp.open('get', 'chartData.do');
      xhtp.send();
      xhtp.onload = function () {
        console.log('3')
        let data = JSON.parse(xhtp.response); //{'admin':3, 'sales':5...'shipping':3}
        // data = {Admin: 3, Sales:6, Shipping:9}
        for (let dept in data) {
          console.log(dept, data[dept]);
          result.push([dept, data[dept]])
        }

        console.log(result);

        data = google.visualization.arrayToDataTable(result);

        var options = {
          title: '부서별 인원 현황',
          pieHole: 0.4,
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    }
    console.log('4')
  </script>
</head>

<body>
  <div id="donutchart" style="width: 900px; height: 500px;"></div>
</body>

</html>