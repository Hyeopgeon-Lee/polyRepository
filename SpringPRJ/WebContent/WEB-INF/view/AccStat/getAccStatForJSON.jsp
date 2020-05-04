<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.AccStatDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List"%>   
<%@ page import="java.util.ArrayList"%> 
<%@ page import="java.util.HashMap"%>  
<%@ page import="java.util.Iterator"%>  
<%
//Controller로부터 전달받은 데이터
Map<String, Object> rMap = (Map<String, Object>)request.getAttribute("rMap");

if (rMap==null){
	rMap = new HashMap<String, Object>();
}
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenAPI 호출 결과</title>
<body>

<hr/><br/>
요청 파라미터(reqYYYYMM) : <%=CmmUtil.nvl((String)rMap.get("reqYYYYMM")) %> <br/> 
요청 파라미터(reqAcode) : <%=CmmUtil.nvl((String)rMap.get("reqAcode")) %> <br/><br/>

<hr/><br/>
요청 결과<br/>

출력 레코드 수 : <%=(Long)rMap.get("recordCnt") %> <br/><br/>
출력 결과<br/><br/>
<%

//배열로 저장된 결과 레코드들
List<AccStatDTO>rList = (List<AccStatDTO>)rMap.get("res");

Iterator<AccStatDTO> it = rList.iterator();

while(it.hasNext()){
	AccStatDTO rDTO = (AccStatDTO)it.next();
	
	out.println("yyyymm : "+ CmmUtil.nvl(rDTO.getYyyymm()) + "<br/>");
	out.println("a_code : "+ CmmUtil.nvl(rDTO.getA_code()) + "<br/>");
	out.println("a_name : "+ CmmUtil.nvl(rDTO.getA_name()) + "<br/>");
	out.println("stat_a : "+ CmmUtil.nvl(rDTO.getStat_a()) + "<br/>");
	out.println("stat_b : "+ CmmUtil.nvl(rDTO.getStat_b()) + "<br/>");
	out.println("-----------------------------<br/>");
	
}

%>

</body>
</html>

