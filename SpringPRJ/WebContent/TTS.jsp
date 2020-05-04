<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/annyang.js"></script>
<script src="/js/jquery-3.4.1.min.js"></script>
<script>
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

	//음성인식 가능한 브라어주인지 확인
    if ('SpeechRecognition' in window) {
    	alert("음성인식 가능함");
    }
    
	//음성 인식결과 가져오기
	recognition.onresult = function(event) {
		var interim_transcript = "";
		final_transcript = "";
		for (var i = event.resultIndex; i < event.results.length; ++i) {
			if (event.results[i].isFinal) {
				final_transcript += event.results[i][0].transcript;
			}
		}
		//$("#view_ing").html("말하는 중 : " + interim_transcript);
		$("#view_msg").html(final_transcript);
		$("#send_msg").val(final_transcript);

		//Ajax 호출
		$.ajax({
			url : '/speach/save.do',
			type : 'post',
			dataType : "JSON",
			data : $("form").serialize(),
			success : function(json) {
				
				var msgResult = "";
				
				for (var i = 0; i < json.length; i++) {
					msgResult += (json[i].msg);
					msgResult += (" | "+ json[i].userHostNm + "("+ json[i].userIP + ") ");
					msgResult += (" | "+ json[i].dateTime + "<br/>");
				}

				$('#total_msg').html(msgResult);
			}
		})

	};
</script>
</head>
<body>
	<h1>내가 방금 한말</h1>
	<hr />
	<div id="view_msg"></div>

	<h1>음성메시지 전체 내용</h1>
	<hr />
	<div id="total_msg"></div>

	<!-- 음성 인식 데이터를 전송하기 위한 폼 -->
	<form name="form" method="post">
		<input type="hidden" name="send_msg" id="send_msg" />
	</form>

</body>
</html>

