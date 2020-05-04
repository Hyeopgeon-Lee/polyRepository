<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
//Controller로부터 전달받은 데이터
String res = CmmUtil.nvl((String)request.getAttribute("res"), "0");
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>서울강서캠퍼스 식단 수집 결과</title>
<body>
서울강서캠퍼스 식단 홈페이지에서 <%=res %>개의 월요일부터 금요일까지의 식단 정보가 수집되었습니다.
</body>
</html>

