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
<title>���� �м� ���</title>
<body>
	<h2>���� �м� ���</h2>
	<hr />
	<br/>
	<%=res%>
</body>
</html>

