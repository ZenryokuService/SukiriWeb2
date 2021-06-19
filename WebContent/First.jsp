<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%


	List list = new ArrayList<>();
	list.add("あああ");
	list.add("いいい");
	list.add("ううう");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Hello World</p>

<%= "Params: " + request.getParameter("name") %><br/>
<% for (int i = 0; i < list.size(); i++) { %>
登場人物：<%= list.get(i) %></br>
<% } %>
</body>
</html>