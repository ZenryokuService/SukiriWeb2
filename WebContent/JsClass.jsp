<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ysg.teacher.tkm.app.db.H2dbManager" %>
<%
	String param = request.getParameter("name");
	String param1 = request.getParameter("left");
	String param2 = request.getParameter("right");

	H2dbManager dao = H2dbManager.getInstance();
	List<String> list = new ArrayList<>();
	dao.executeQuery("SELECT * FROM TEST;", list);

%>
<!doctype html>
<html>
    <head>
        <title>kadai1</title>
        <meta charset="utf-8">
        <script>
            function answers(){
                console.log("test1");
                let n1 = document.getElementById("n1").value;
                let s1 = document.getElementById("s1").value;
                let n2 = document.getElementById("n2").value;
                console.log(s1);
                let num1 = parseInt(n1);
                let num2 = parseInt(n2);
                let ans;
                console.log("test2");
                if (s1 == '+') {
                    ans = num1 + num2;
                    console.log("test3");
                } if (s1 == '-'){
                    ans = num1 - num2;
                    console.log("test4");
                } if (s1 == '*') {
                    ans = num1 * num2;
                    console.log("test5");
                } if (s1 == '/') {
                    if(num2 == 0){
                        console.log("test6");
                        ans = "error";
                    } else {
                        ans = num1 / num2;
                        console.log("test7");
                    }
                }
                document.getElementById("t1").value = ans;
                console.log("test8");
            }
        </script>
    </head>
    <body>
    Param: <%= param %>
        <form id="f1" action="/SukiriWeb2/hello" method="post">
            <input type="number" id="n1" name="left" value="<%= param1 %>"></input>
            <select name="sisoku" id="s1">
                <option value="+">+</option>
                <option value="-">-</option>
                <option value="*">*</option>
                <option value="/">/</option>
            </select>
            <input type="number" id="n2" name="right" value="<%= param2 %>"></input>
            <input type="button" value="=" onclick="answers()"/>
            <input type="text" size=20 id="t1" name="ans"/>
	        <input type="submit" value="サーバーへ送信"/>
        </form>
        <% for (String s : list) { %>
				<%= s %> <br/>
		<% } %>
    </body>
</html>