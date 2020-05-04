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
			url : "/melon/getCompareRank.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {

				var melon_rank = "";

				for (var i = 0; i < json.length; i++) {
					melon_rank += (json[i].song + " | ");
					melon_rank += (json[i].singer + " | ");
					melon_rank += ("현재 : " + json[i].current_rank + "위 | ");
					melon_rank += ("이전 : " + json[i].pre_rank + "위 | ");

					var change_rank = json[i].change_rank;

					if (change_rank.indexOf("하강") > 0) { //순위 하락은 파란색
						melon_rank += ("<font color='blue'>" + json[i].change_rank + "</font><br>");
					
					} else { //순위 상승과, 신규 진입은 빨간색
						melon_rank += ("<font color='red'>"+ json[i].change_rank + "</font><br>");
					}
				}

				$("#melon_rank").html(melon_rank);
			}
		})

	}
</script>
</head>
<body>
	<h1>멜론 노래별 이전 랭킹과 비교</h1>
	<hr />
	<div id="melon_rank"></div>
	<br />
	<hr />
</body>
</html>



