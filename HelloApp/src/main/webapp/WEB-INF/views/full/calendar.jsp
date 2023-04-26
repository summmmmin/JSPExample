<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='fullcal/dist/index.global.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    //allEvents 초기
    let allEvents = [{title:'title1', start:'2023-04-05', end:'2023-04-10'}]
    
    //서버의 json 데이터를 가져오기 위한 fetch
    fetch('eventList.do')
	.then(resolve => resolve.json())	//{"title":"test, "startDate":"2023-04-05"}
	.then(result => {
		console.log(result);
		result.forEach(function(event) {
			let newEvent = {
					title: event.title,
					start: event.startDate,
					end: event.endDate
			}
			allEvents.push(newEvent);	//
    	});
    	// forEach
		
            var calendar = new FullCalendar.Calendar(calendarEl, {

              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
              },
              initialDate: '2023-01-12',
              navLinks: true, // can click day/week names to navigate views
              selectable: true,
              selectMirror: true,
              select: function(arg) {
                var title = prompt('Event Title:');
                if (title) {
                	console.log(arg)
                	//Ajax호출
                	fetch('addEvent.do',{
                		method:"POST",
                		headers:{
                			'Content-Type': 'application/x-www-form-urlencoded',
                		},
                		body: 'title='+title+'&start_date=+'+arg.startStr+'&end_date='+arg.endStr
                	})
                	.then(resolve => resolve.json())
                	.then(result => {
                		if(result.retCode == 'Success'){
                			//화면에 추가된 이벤트 등록
                            calendar.addEvent({
                              title: title,
                              start: arg.start,
                              end: arg.end,
                              allDay: arg.allDay
                            })
                		}else{
                			alert('실패!');
                		}
                	})
                	.catch(err => console.log(err))
                	
                }
                calendar.unselect()
              },
              eventClick: function(arg) {
                if (confirm('Are you sure you want to delete this event?')) {
                  arg.event.remove()
                }
              },
              editable: true,
              dayMaxEvents: true, // allow "more" link when too many events
              events: allEvents
            });	                    
            calendar.render();
            // end..
	})
	.catch(error => console.log(error))
        })
    
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>