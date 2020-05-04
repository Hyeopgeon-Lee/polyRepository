<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이미지로부터 텍스트 인식을 위한 파일 업로드 페이지</title>
<body>
	<h2>이미지 인식</h2>
	<hr/>
	<form name="form1" method="post" enctype="multipart/form-data"	action="/ocr/getReadforImageText.do">
		<br />
		이미지 파일 업로드 : <input type="file" name="fileUpload" />
		<br />
		<br />
		<input type="submit" value="전송" />
	</form>
</body>
</html>

