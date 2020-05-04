<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%

//전달받은 메시지
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>처리페이지</title>
<script type="text/javascript">

	alert("<%=msg%>");
	top.location.href="/notice/NoticeList.do";
		
</script>
</head>
<body>

</body>
</html>