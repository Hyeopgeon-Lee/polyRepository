<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<%
	String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/annyang.js"></script>
<script src="/js/jquery-3.4.1.min.js"></script>
<script>
	// html의 모든 객체(태그)들이 로딩이 완료된 경우 실행함
	$(window).on("load", function() {

		//화면 로딩이 완료되면 첫번째로 실행함
		getRoomList();
		
		//2번쨰부터 채팅방 전체리스트를 5초마다 로딩함
		setInterval("getRoomList()", 5000);

	});

	//전체 채팅방 리스트 가져오기(채팅 내용도 같이 가져옴)
	function getRoomList() {
		//Ajax 호출
		$.ajax({
			url : "/chat/roomList.do",
			type : "post",
			dataType : "JSON",
			success : function(json) {

				var roomList = "";

				for (var i = 0; i < json.length; i++) {
					roomList += (json[i] + "<br>");

				}

				$("#room_list").html(roomList);

				/*
				Ajax는 비동기 처리 기술로 동시에 여러개의 Ajax가 실행되면, 충돌나서 에러 발생함
				보통 자바스크립트에서 제공하는 Promise를 통해 할수 있지만, 
				간단한 화면은 Ajax의 실행 성공 함수에 함수 추가하는 것이 좋음 
				 */
				getAllMsg(); //전체 대화 가져오기
			}
		})

	}

	//채팅방 전체 대화 가져오기
	function getAllMsg() {

		//Ajax 호출
		$.ajax({
			url : "/chat/getMsg.do",
			type : "post",
			dataType : "JSON",
			data : $("form").serialize(),
			success : function(json) {

				$("#view_json").html("getAllMsg : " + json);

				//메시지 출력하기
				doOutputMsg(json);
			}
		})
	}

	// 대화 메시지 전체 출력하기
	function doOutputMsg(json) {

		// 메시지 대화가 존재하면 출력
		if (json != null) {
			var msgResult = "";

			for (var i = 0; i < json.length; i++) {
				msgResult += (json[i].msg);
				msgResult += (" | " + json[i].userNm)
				msgResult += (" | " + json[i].dateTime + "<br/>");
			}

			$("#total_msg").html(msgResult);

		}
	}

	/*
	#########################################################################
									음성 인식 관련 소스 시작
	#########################################################################
	 */
	//annyang 라이브러리 실행
	annyang.start({
		autoRestart : true,
		continuous : true
	})

	//음성인식 값 받아오기위한 객체 생성
	var recognition = annyang.getSpeechRecognizer();

	//최종 음성인식 결과값 저장 변수
	var final_transcript = "";

	//말하는 동안에 인식되는 값 가져오기(허용)
	recognition.interimResults = false;

	//음성 인식결과 가져오기
	recognition.onresult = function(event) {
		var interim_transcript = "";
		final_transcript = "";
		for (var i = event.resultIndex; i < event.results.length; ++i) {
			if (event.results[i].isFinal) {
				final_transcript += event.results[i][0].transcript;
			}
		}
		$("#view_msg").html(final_transcript);
		$("#send_msg").val(final_transcript);

		//Ajax 호출
		$.ajax({
			url : "/chat/msg.do",
			type : "post",
			dataType : "JSON",
			data : $("form").serialize(),
			success : function(json) {

				$("#view_json").html("msg : " + json);

				//메시지 출력하기
				doOutputMsg(json);
			}
		})

	};

	/*
	#########################################################################
									음성 인식 관련 소스 끝
	#########################################################################
	 */
</script>
</head>
<body>
	<h1>
		[<%=room_name%>] 채팅방 '음성대화' 전체 내용
	</h1>
	<hr />
	<div id="total_msg"></div>

	<h1>내가 방금 말한 전송 메시지</h1>
	<hr />
	<div id="view_msg"></div>
	<br />
	<h1>채팅방 전체 리스트</h1>
	<hr />
	<div id="room_list"></div>

	<!-- 음성 인식 데이터를 전송하기 위한 폼 -->
	<form name="form" method="post">
		<input type="hidden" name="send_msg" id="send_msg" />
	</form>

</body>
</html>

