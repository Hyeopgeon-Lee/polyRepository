<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
//Controller�� ����� �������� �α����Ҷ� ������
String SS_USER_ID = CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));

String res = CmmUtil.nvl((String)request.getAttribute("res"));

%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��εǾ����ϴ�.</title>
<%
String msg = "";

if (res.equals("1")){
	msg = SS_USER_ID +"���� �α��� �Ǿ����ϴ�.";
	
}else if (res.equals("0")){
	msg = "���̵�, ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	
}else{
	msg = "�ý��ۿ� ������ �߻��Ͽ����ϴ�. ����� �ٽ� �õ��Ͽ� �ֽñ� �ٶ��ϴ�.";
	
}


%>
</head>
<body>
<%=msg %>
</body>
</html>