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
	
</style>
</head>
<body>

	<%
	request.setCharacterEncoding("UTF-8");
	
	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userCar = request.getParameter("car");
	String[] userCars = request.getParameterValues("cars");
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
			<td>자동차</td>
			<td><%= userCar %></td>
		</tr>
		<tr>
			<td>자동차들</td>
			<td>
			<%
			for(String car : userCars){
			%>
			<%= car %>
			<%} %>
			</td>
		</tr>
	</table>

</body>
</html>