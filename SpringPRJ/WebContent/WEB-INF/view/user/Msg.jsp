<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.UserInfoDTO" %>
<%
//Controller�κ��� ���޹��� ������
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));

//Controller�κ��� ���޹��� ��(ȸ������ �Է�ȭ��)���κ��� �Է¹��� ������(ȸ�����̵�, �̸�, �̸���, �ּ� ��)
UserInfoDTO pDTO = (UserInfoDTO)request.getAttribute("pDTO");

if (pDTO==null){
	pDTO = new UserInfoDTO();
	
}

%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�������� ���ϵ帳�ϴ�.</title>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
</head>
<body>
<%=CmmUtil.nvl(pDTO.getUser_name()) %>���� ȸ�������� ���ϵ帳�ϴ�.
</body>
</html>