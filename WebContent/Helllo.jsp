<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="sukiri.data.MyData" %>

<%
	String name = request.getParameter("name");
	String age = request.getParameter("age");

	MyData data = (MyData) request.getAttribute("data");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String st = "Hello World"; %>

こんにちは<%= st %><br/>

＜Data＞<br/>
名前：<%= data.getName() %>
年齢：<%= data.getAge() %>

</body>
</html>