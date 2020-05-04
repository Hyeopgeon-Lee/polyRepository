<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
//Controller에 저장된 세션으로 로그인할때 생성됨
String SS_USER_ID = CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));

String res = CmmUtil.nvl((String)request.getAttribute("res"));

%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인되었습니다.</title>
<%
String msg = "";

if (res.equals("1")){
	msg = SS_USER_ID +"님이 로그인 되었습니다.";
	
}else if (res.equals("0")){
	msg = "아이디, 비밀번호가 일치하지 않습니다.";
	
}else{
	msg = "시스템에 문제가 발생하였습니다. 잠시후 다시 시도하여 주시길 바랍니다.";
	
}


%>
</head>
<body>
<%=msg %>
</body>
</html>