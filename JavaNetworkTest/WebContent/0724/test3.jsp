<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSP : Java Server Page</h1>
	
	<% 
		request.setCharacterEncoding("UTF-8");

		String userName = request.getParameter("name");
		String userGender = request.getParameter("gender");
		String[] fruits = request.getParameterValues("fruit");
		
//		String str = "";
		
//		for(String fr : fruits){
//			str += fr;
//			str += " ";
//		}
	%>
	
	<table>
		<colgroup>
			<col width="30%">
			<col width="*">
		</colgroup>
		<tr>
			<th>이름</th>
			<td><%= userName %></td>
		</tr>
		<tr>
			<th>성별</th>
			<td><%= userGender %></td>
		</tr>
		<tr>
			<th>과일</th>
			<td>
			<%
				for(int i = 0; i < fruits.length; i++){
			%>
				<span> <%= fruits[i] %> </span>
			<%
				}
			%>
			</td>
		</tr>
	</table>

</body>
</html>