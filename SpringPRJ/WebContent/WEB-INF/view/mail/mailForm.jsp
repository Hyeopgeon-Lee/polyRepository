<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����������</title>
</head>
<body>
	<form name="f" method="post" action="/mail/sendMail.do">
		<table border="1" style="width:500px">
			<tr>
				<td style="width:100px">�޴»��</td>
				<td style="width:400px"><input type="text" name="toMail" style="width:380px" /></td>
			</tr>
			<tr>
				<td style="width:100px">��������</td>
				<td><input type="text" name="title" style="width:380px" /></td>
			</tr>
			<tr>
				<td style="width:100px">���ϳ���</td>
				<td><textarea name="contents" rows="5" style="width:380px"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="[��������]" />
		<input type="reset" value="[�����ʱ�ȭ]" />
	</form>
</body>
</html>