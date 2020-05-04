<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenAPI 호출하기</title>
<!-- 반드시 라이브러리 추가는 로컬에 다운로드해서 절대 경로로 기입함(상대 경로 사용 금지) -->
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	//HTML document 구조가 완료될 때 실행
	$(document).ready(function(){
		
		//id가 excute인 항목에 대해 클릭 이벤트 생성 
		$("#execute").click(function() {
			$.ajax({
				url : '/AccStat/getAccStatNightForJSON.do',
				type : 'post',
				dataType:"JSON",
				data : $("form").serialize(),
				success : function(json) {
					$('#display_json').text(json);
					$('#display_reqYYYYMM').text(json.reqYYYYMM);
					$('#display_reqAcode').text(json.reqAcode);
					$('#display_recordCnt').text(json.recordCnt);
				
					var resHTML = "";
					var resObj = json.res; //배열형태 데이터
					for (var i=0;i<resObj.length;i++){
						resHTML += ("{yyyymm: "+ json.res[i].yyyymm + ", ");
						resHTML += ("a_code: "+ json.res[i].a_code + ", ");
						resHTML += ("a_name: "+ json.res[i].a_name + ", ");
						resHTML += ("stat_a: "+ json.res[i].stat_a + "}");
					}
					
					$('#display_res').text(resHTML);
				}
			})
		})
	});

</script>
</head>
<body>
	OpenAPI 호출하기
	<hr />
	<br />

	<form name="form" method="post">
		교통사고년월 : <input type="text" name="yyyymm" maxlength="6" /> <br />

		교통사고구분 : 
		<select name="a_code">
			<option value="">전체</option>
			<option value="A">사고건수</option>
			<option value="B">사망자수</option>
			<option value="C">부상자수</option>
		</select> <br /> <br />
		<input type="button" id="execute" value="RestAPI 호출은 WAS를 통해!!" />
	</form>
	<br/><br/>
	<hr/>
	JSON : <span id="display_json" style="width:300px"></span><br/>
	reqYYYYMM : <span id="display_reqYYYYMM" style="width:300px"></span><br/>
	reqAcode : <span id="display_reqAcode" style="width:300px"></span><br/>
	recordCnt : <span id="display_recordCnt" style="width:300px"></span><br/>
	res : <span id="display_res" style="width:100%"></span><br/>
</body>
</html>

