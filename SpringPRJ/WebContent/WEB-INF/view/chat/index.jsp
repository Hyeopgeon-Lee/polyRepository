<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	var myChatRoom = "";

	$(window).on('load', function() {

		//전체 채팅방 리스트 가져오기
		getRoomList();
	});

	//전체 채팅방 리스트 가져오기
	function getRoomList() {

		//Ajax 호출
		$.ajax({
			url : '/chat/roomList.do',
			type : 'post',
			dataType : "JSON",
			contentType:"application/json; charset=UTF-8",
			success : function(json) {

				var roomList = "";

				for (var i = 0; i < json.length; i++) {
					roomList += (json[i] + "<br>");

				}

				$('#room_list').html(roomList);
			}
		})

	}
</script>
</head>
<body>
	<h1>채팅방 전체 리스트</h1>
	<hr />
	<div id="room_list"></div>
	<br />
	<br />
	<h1>채팅방 입장 정보</h1>
	<hr />
	<form name="form" method="post" action="/chat/intro.do">
		채팅방 이름 : <input type="text" name="room_name" id="room_name" /> <br />
		채팅자 이름 : <input type="text" name="user_name" id="user_name" /> <br />
		<input type="submit" value="입장" />
	</form>

</body>
</html>

