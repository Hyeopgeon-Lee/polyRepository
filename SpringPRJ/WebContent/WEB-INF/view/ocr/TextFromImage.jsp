<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil"%>
<%
	//Controller�κ��� ���޹��� ������
	String res = CmmUtil.nvl((String) request.getAttribute("res"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�̹����κ��� �ؽ�Ʈ �ν� ���</title>
<body>
	<h2>�̹��� �ν� ���</h2>
	<hr />
	�̹����κ��� �ؽ�Ʈ �ν� ����� <%=res%> �Դϴ�.
</body>
</html>

