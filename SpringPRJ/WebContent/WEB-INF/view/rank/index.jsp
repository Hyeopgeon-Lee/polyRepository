<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/annyang.js"></script>
<script src="/js/jquery-3.4.1.min.js"></script>
<script>
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
			url : "/rank/movie.do",
			type : "post",
			dataType : "JSON",
			data : $("form").serialize(),
			success : function(json) {

				var msgResult = "";

				for (var i = 0; i < json.length; i++) {
					msgResult += ("순위 : " + json[i].seq + "<br/>");
					msgResult += ("영화제목 : " + json[i].movie_nm + "<br/>");
					msgResult += ("영화평점 : " + json[i].score + "<br/>");
					msgResult += ("예매율 : " + json[i].movie_reserve + "<br/>");
					msgResult += ("개봉일 : " + json[i].open_day + "<br/>");
					msgResult += ("<hr/>");
				}

				$("#cgv_rank_list").html(msgResult);
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
	<h1>내가 방금 말한 음성명령</h1>
	<hr />
	<div id="view_msg"></div>
	<br />
	<h1>영화 순위 결과</h1>
	<hr />
	<div id="cgv_rank_list"></div>

	<!-- 음성 인식 데이터를 전송하기 위한 폼 -->
	<form name="form" method="post">
		<input type="hidden" name="send_msg" id="send_msg" />
	</form>

</body>
</html>

