<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.NoticeDTO" %>
<%@ page import="java.util.List"%>   
<%@ page import="java.util.ArrayList"%> 
<%@ page import="java.util.HashMap"%>    
<%
session.setAttribute("SESSION_USER_ID", "USER01"); //���� ���� ����, �α��ε� ���·� �����ֱ� ����

List<NoticeDTO> rList =	(List<NoticeDTO>)request.getAttribute("rList");

//�Խ��� ��ȸ ��� �����ֱ�
if (rList==null){
	rList = new ArrayList<NoticeDTO>();
	
}

%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� ����Ʈ</title>
<script type="text/javascript">

//�󼼺��� �̵�
function doDetail(seq){
	location.href="/notice/NoticeInfo.do?nSeq="+ seq;
}

</script>	
</head>
<body>
<h2>��������</h2>
<hr/>
<br/>

<table border="1" width="600px">
<tr>
	<td width="100" align="center">����</td>
	<td width="200" align="center">����</td>
	<td width="100" align="center">��ȸ��</td>
	<td width="100" align="center">�����</td>
	<td width="100" align="center">�����</td>
</tr>
<%
for (int i=0;i<rList.size();i++){
	NoticeDTO rDTO = rList.get(i);

	if (rDTO==null){
		rDTO = new NoticeDTO();
	}
	
%>
<tr>
	<td align="center">
	<%
	//�������̶��, [����]ǥ�� 
	if (CmmUtil.nvl(rDTO.getNotice_yn()).equals("1")){
		out.print("<b>[����]</b>");
		
	//�������� �ƴ϶��, �۹�ȣ �����ֱ� 		
	}else{
		out.print(CmmUtil.nvl(rDTO.getNotice_seq()));
			
	}
	%></td>
	<td align="center">
		<a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getNotice_seq())%>');">
		<%=CmmUtil.nvl(rDTO.getTitle()) %></a>
	</td>
	<td align="center"><%=CmmUtil.nvl(rDTO.getRead_cnt()) %></td>
	<td align="center"><%=CmmUtil.nvl(rDTO.getUser_name()) %></td>
	<td align="center"><%=CmmUtil.nvl(rDTO.getReg_dt()) %></td>
</tr>
<%
}
%>
</table>
<a href="/notice/NoticeReg.do">[�۾���]</a>
</body>
</html>