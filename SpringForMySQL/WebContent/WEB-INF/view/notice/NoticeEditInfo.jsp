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

int access = 1; //(�ۼ��� : 2 / �ٸ� �����: 1) 

if (CmmUtil.nvl((String)session.getAttribute("SESSION_USER_ID")).equals(
		CmmUtil.nvl(rDTO.getUser_id()))){
	access = 2;
}
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ��� �۾���</title>
<script type="text/javascript">

//�ۼ��� ����üũ
function doOnload(){
	
	if ("<%=access%>"=="1"){
		alert("�ۼ��ڸ� ������ �� �ֽ��ϴ�.");
		location.href="/notice/NoticeList.do";
		
	}
}

//���۽� ��ȿ�� üũ
function doSubmit(f){
	if(f.title.value == ""){
		alert("������ �Է��Ͻñ� �ٶ��ϴ�.");
		f.title.focus();
		return false;
	}
	
	if(calBytes(f.title.value) > 200){
		alert("�ִ� 200Bytes���� �Է� �����մϴ�.");
		f.title.focus();
		return false;
	}	
	
	var noticeCheck = false; //üũ ���� Ȯ�� ����
	for(var i=0;i<f.noticeYn.length;i++){
		if (f.noticeYn[i].checked){
			noticeCheck = true;
		}
	}
	
	if(noticeCheck==false){
		alert("������ ���θ� �����Ͻñ� �ٶ��ϴ�.");
		f.noticeYn[0].focus();
		return false;
	}	
	
	if(f.contents.value == ""){
		alert("������ �Է��Ͻñ� �ٶ��ϴ�.");
		f.contents.focus();
		return false;
	}	
	
	if(calBytes(f.contents.value) > 4000){
		alert("�ִ� 4000Bytes���� �Է� �����մϴ�.");
		f.contents.focus();
		return false;
	}		
	
	
}

//���� ���� ����Ʈ ������ üũ�ϱ�(����Ʈ�� ����)
function calBytes(str){
	
	var tcount = 0;
	var tmpStr = new String(str);
	var strCnt = tmpStr.length;

	var onechar;
	for (i=0;i<strCnt;i++){
		onechar = tmpStr.charAt(i);
		
		if (escape(onechar).length > 4){
			tcount += 2;
		}else{
			tcount += 1;
		}
	}
	
	return tcount;
}

</script>	
</head>
<body onload="doOnload();">
<h2>�� ����!</h2>
<form name="f" method="post" action="/notice/NoticeUpdate.do" onsubmit="return doSubmit(this);">
<input type="hidden" name="nSeq" value="<%=CmmUtil.nvl(request.getParameter("nSeq")) %>" />
	<table border="1">
		<col width="100px" />
		<col width="500px" />
		<tr>
			<td align="center">����</td>
			<td>
			<input type="text" name="title" maxlength="100" 
				   value="<%=CmmUtil.nvl(rDTO.getTitle()) %>" style="width: 450px"/>
			</td>
		</tr>
		<tr>
			<td align="center">������ ����</td>
			<td>
				��<input type="radio" name="noticeYn" value="1"
						<%=CmmUtil.checked(CmmUtil.nvl(rDTO.getNotice_yn()), "1") %>	/>
			        �ƴϿ�<input type="radio" name="noticeYn" value="2" 
			        	<%=CmmUtil.checked(CmmUtil.nvl(rDTO.getNotice_yn()), "2") %>	/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea 
						name="contents" style="width: 550px; height: 400px"
						><%=CmmUtil.nvl(rDTO.getContents()) %></textarea>
			</td>
		</tr>
	<tr>
		<td align="center" colspan="2">
			<input type="submit" value="����" />
			<input type="reset" value="�ٽ� �ۼ�" />
		</td>
	</tr>		
	</table>
</form>	
</body>
</html>