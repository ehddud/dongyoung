<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		font-size: 0.8em;
	}
	
	span{
		color : red;	
	}
	h1{
		color : blue;
	}
</style>
</head>

<body>

	<h1>JSP : Java Server Page</h1>
	
	<%
	request.setCharacterEncoding("UTF-8");
		
	String userId = request.getParameter("id");
	String userPw = request.getParameter("pw");
	
	%>
	
	<div>
		<span><%= userId %></span>님 환영합니다.<br>
		비밀번호 : <span><%= userPw %></span>
	</div>

</body>
</html>