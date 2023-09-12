<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: 1px solid green;
		border-collapse : collapse;
	}
	td{
		width: 300px;
		height: 50px;
		border: 1px solid green;
		text-align: center;
	}
	th{
		height:30px;
		border: 1px solid green;
		background-color : skyblue;
	}
</style>
</head>
<body>
	<h1>JSP : Java Server Page</h1>
	<%
	String userId = request.getParameter("id");
	String userPass = request.getParameter("pw");
	
	// 서버의 DB와 연결해서 table 데이터를 검색
	// 결과를 응답 페이지로 생성한다
	%>
	
	<table>
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
		</tr>
		<tr>
			<td><%= userId %></td>
			<td><%= userPass %></td>
		</tr>
	</table>
</body>
</html>