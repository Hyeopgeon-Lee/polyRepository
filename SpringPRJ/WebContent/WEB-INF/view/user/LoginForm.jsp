<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ȭ��</title>

<script type="text/javascript">

	//�α����� ���� �Է������� ��ȿ�� üũ�ϱ�
	function doLoginUserCheck(f){
		
		if (f.user_id.value==""){
			alert("���̵� �Է��ϼ���.");
			f.user_id.focus();
			return false;
		}
		
		if (f.password.value==""){
			alert("��й�ȣ�� �Է��ϼ���.");
			f.password.focus();
			return false;
		}
		
	}
</script>


</head>
<body>

<h1>�α��� ȭ��</h1>
<br/>
<br/>
<form name="f" method="post" action="/user/getUserLoginCheck.do" onsubmit="return doLoginUserCheck(this);">
<table border="1">
<col width="150px">
<col width="150px">
<col width="150px">
<col width="150px">
<tr>
	<td>���̵�</td>
	<td><input type="text" name="user_id" style="width:150px"/></td>
	<td>��й�ȣ</td>
	<td><input type="password" name="password" style="width:150px"/></td>
</tr>
</table>
<input type="submit" value="�α���" />
</form>
</body>
</html>