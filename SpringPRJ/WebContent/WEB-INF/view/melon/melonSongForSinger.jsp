<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(window).on("load", function() {

		//페이지 로딩 완료 후, 멜론 순위가져오기 함수 실행함 
		getRank();
	});

	function getRank() {

		//Ajax 호출
		$.ajax({
			url : "/melon/getSongForSinger.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {

				var melon_rank = "";

				for (var i = 0; i < json.length; i++) {
					melon_rank += (json[i].rank + "위 | ");
					melon_rank += (json[i].song + " <br>");

				}

				$('#melon_rank').html(melon_rank);
			}
		})

	}
</script>
</head>
<body>
	<h1>멜론 top100에 랭크된 방탄소년단의 노래 제목가져오기</h1>
	<hr />
	<div id="melon_rank"></div>
	<br />
	<hr />
</body>
</html>


