<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�̹����κ��� �ؽ�Ʈ �ν��� ���� ���� ���ε� ������</title>
<body>
	<h2>�̹��� �ν�</h2>
	<hr/>
	<form name="form1" method="post" enctype="multipart/form-data"	action="/ocr/getReadforImageText.do">
		<br />
		�̹��� ���� ���ε� : <input type="file" name="fileUpload" />
		<br />
		<br />
		<input type="submit" value="����" />
	</form>
</body>
</html>

