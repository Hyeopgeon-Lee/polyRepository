<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>감정 분석을 위한 문장 입력폼</title>
<body>
	<h2>오피니언 마이닝 - 감정 분석</h2>
	<hr/>
	<form name="form1" method="post" action="/nlp/wordAnalysis.do">
		<br />
		분석 메시지<br/>
		<input type="text" name="text_message" style="width:400px"/>
		<br />
		<br />
		<input type="submit" value="전송" />
	</form>
</body>
</html>


