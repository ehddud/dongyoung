<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	span {
		color: red;
	}
	table{
		border: 2px solid green;
	}
	
	td{
		width:300px;
		height:50px;
		border: 1px solid green;
		text-align:left;
	}
	
	.autoH {
		height: auto;
	}
	
</style>
</head>
<body>
	
	<h1>JSP : Java Server Page</h1>

	<%
	request.setCharacterEncoding("UTF-8");
	
	String userName = request.getParameter("name");
	String userId = request.getParameter("id");
	String userIntro = request.getParameter("introduce");
	String[] food = request.getParameterValues("food");
	// 엔터기호 \r\n를 <br>태그로 변경 => replaceAll
	userIntro = userIntro.replaceAll("\r\n", "<br>");
	
	
	// db접속 - crud 처리
	
	// 결과값을 얻는다
	
	// 결과값으로 출력
	
	%>
	
	<table>
		<tr>
			<td>아이디</td>
			<td><%= userId %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%= userName %></td>
		</tr>
		<tr>
			<td>소개</td>
			<td><%= userIntro %></td>
		</tr>
		<tr>
			<td>음식</td>
			<td>
			<%
			for(int i = 0; i < food.length; i++){
				
			%>
			<%= food[i] %>
			<%
			}
			%>
			</td>
		</tr>
	</table>

</body>
</html>