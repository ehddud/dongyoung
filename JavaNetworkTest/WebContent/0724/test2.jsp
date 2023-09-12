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
	border-collapse: collapse;
}

td {
	width: 300px;
	height: 50px;
	border: 1px solid green;
	padding-left:10px
}

th {
	height: 30px;
	border: 1px solid green;
	background-color: #ccc;
}
</style>
</head>
<body>
	<h1>JSP : Java Server Page</h1>
	<%

	request.setCharacterEncoding("UTF-8");
	
	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userAddr = request.getParameter("addr");
	String userTel = request.getParameter("tel");
	String userAge = request.getParameter("age");
	String userFile = request.getParameter("file");
	String userRadio = request.getParameter("radio");
	String userChk = request.getParameter("checkbox");
	
	
	// db crud 처리 결과를 얻어서
	// 응답 데이터를 생성한다
	%>

	<table>
		<colgroup>
			<col width="30%">
			<col width="*">
		</colgroup>
		<tr>
			<th>아이디</th>
			<td><%= userId %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%= userName %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%= userAddr %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%= userTel %></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><%= userAge %></td>
		<tr>
		<tr>
			<th>파일</th>
			<td><%= userFile %></td>
		<tr>
		<tr>
			<th>라디오</th>
			<td><%= userRadio %></td>
		<tr>
		<tr>
			<th>체크박스</th>
			<td><%= userChk %></td>
		<tr>
	</table>
</body>
</html>