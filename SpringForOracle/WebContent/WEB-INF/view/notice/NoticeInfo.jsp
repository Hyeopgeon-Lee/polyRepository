<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.NoticeDTO" %>
<%
NoticeDTO rDTO = (NoticeDTO)request.getAttribute("rDTO");

//������ ������ ���ҷ��Դٸ�, ��ü ����
if (rDTO==null){
	rDTO = new NoticeDTO();

}

String ss_user_id = CmmUtil.nvl((String)session.getAttribute("SESSION_USER_ID"));

//������ �ۼ��� �۸� ���� �����ϵ��� �ϱ�(1:�ۼ��� �ƴ� / 2: ������ �ۼ��� �� / 3: �α��ξ���)
int edit = 1;

//�α��� ���ߴٸ�....
if (ss_user_id.equals("")){
	edit = 3;
	
//������ �ۼ��� ���̸� 2�� �ǵ��� ����
}else if (ss_user_id.equals(CmmUtil.nvl(rDTO.getUser_id()))){
	edit = 2;
	
}

System.out.println("user_id : "+ CmmUtil.nvl(rDTO.getUser_id()));
System.out.println("ss_user_id : "+ss_user_id);

%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ��� �ۺ���</title>
<script type="text/javascript">

//�����ϱ�
function doEdit(){
	if ("<%=edit%>"==2){
		location.href="/notice/NoticeEditInfo.do?nSeq=<%=CmmUtil.nvl(rDTO.getNotice_seq())%>";
		
	}else if ("<%=edit%>"==3){
		alert("�α��� �Ͻñ� �ٶ��ϴ�.");
		
	}else {
		alert("������ �ۼ��� �۸� ���� �����մϴ�.");
		
	}
}


//�����ϱ�
function doDelete(){
	if ("<%=edit%>"==2){
		if(confirm("�ۼ��� ���� �����Ͻðڽ��ϱ�?")){
			location.href="/notice/NoticeDelete.do?nSeq=<%=CmmUtil.nvl(rDTO.getNotice_seq())%>";
			
		}
		
	}else if ("<%=edit%>"==3){
		alert("�α��� �Ͻñ� �ٶ��ϴ�.");
		
	}else {
		alert("������ �ۼ��� �۸� ���� �����մϴ�.");
		
	}
}
//������� �̵�
function doList(){
	location.href="/notice/NoticeList.do";
		
}

</script>	
</head>
<body>
<table border="1">
	<col width="100px" />
	<col width="200px" />
	<col width="100px" />
	<col width="200px" />
	<tr>
		<td align="center">����</td>
		<td colspan="3"><%=CmmUtil.nvl(rDTO.getTitle())%></td>
	</tr>
	<tr>
		<td align="center">������ ����</td>
		<td colspan="3">��<input type="radio" name="noticeYn" value="1" 
				<%=CmmUtil.checked(CmmUtil.nvl(rDTO.getNotice_yn()), "1") %>/>
		        �ƴϿ�<input type="radio" name="noticeYn" value="2" 
		        <%=CmmUtil.checked(CmmUtil.nvl(rDTO.getNotice_yn()), "2") %>/>
		</td>
	</tr>
	<tr>
		<td align="center">�ۼ���</td>
		<td><%=CmmUtil.nvl(rDTO.getReg_dt())%></td>
		<td align="center">��ȸ��</td>
		<td><%=CmmUtil.nvl(rDTO.getRead_cnt())%></td>
	</tr>	
	<tr>
		<td colspan="4" height="300px" valign="top">
		<%=CmmUtil.nvl(rDTO.getContents()).replaceAll("\r\n", "<br/>") %>
		</td>
	</tr>
<tr>
	<td align="center" colspan="4">
		<a href="javascript:doEdit();">[����]</a>
		<a href="javascript:doDelete();">[����]</a>
		<a href="javascript:doList();">[���]</a>
	</td>
</tr>		
</table>
</body>
</html>