<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sukiri.data.WebConst" %>
<%@ page import="ysg.teacher.tkm.app.db.H2dbManager" %>
<%@ page import="sukiri.data.WebConst" %>

<%
	List<String> list = new ArrayList<>();
	H2dbManager dao = H2dbManager.getInstance();
	dao.executeQuery("SELECT user_name FROM USER_TBL", list);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <title>Procon Quest Title</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="main.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>

<div class="jumbotron text-center">
  <h1>プロコンクエスト</h1>
  <p>～JavaサーブレットでRPG的な～</p>
</div>

<div class="container" style="max-width: 100%;">
  <!-- Top row -->
  <div id="status_view" class="row align-items-center">
    <div class="col-sm-3"></div>
    <div class="col-sm-3"></div>
    <div class="col-sm-3"></div>
    <div class="col-sm-3"></div>
  </div>
  <hr/>
  <!-- Middle row -->
  <div class="row" style="height: 300px">
    <!-- MessageView -->
    <div id="message_view" class="col-centered col-sm-12">
      	<% for (int i = 0; i < list.size(); i++) { %>
      	      <span class="col-sm-3" class="col-centered col-sm-12">Player<%= i +1 %>: <%= list.get(i) %></span>
      	<% } %>
    </div>
    <!-- LoginView -->
    <div id="login_view" class="col-centered col-sm-12" style="display:none;">
      <form action="/SukiriWeb2/rpg" method="post">
        <!-- UserName -->
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">name</span>
          </div>
          <input type="text" name="userName" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
        </div>
        <!-- Password -->
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">passwd</span>
          </div>
          <input type="text" name="passwd" class="form-control" placeholder="Passwd" aria-label="Passwd" aria-describedby="basic-addon1">
        </div>
        <!-- Login button -->
        <div id="login_button" class="col-centered col-sm-6">
          <label><button type="submit" name="pageId" value="/addUser" class="btn btn-success">Add User</button></label>
          <label><button type="button" onclick="toTop()" class="btn btn-info">Back</button></label>
        </div>
      </form>
    </div>
  </div>
  <!-- Bottom row -->
  <div class="row" style="height: 100%">
    <!-- Start or Continue -->
    <div id="start_continue" class="col-centered col-sm-12">
      <form action="/SukiriWeb2/rpg" method="post">
        <label><button type="button" onclick="loginMode();" value="start" class="btn btn-primary">Start</button></label>
        <label><button type="submit" value="continue" class="btn btn-success">Continue</button></label>
      </form>
    </div>
  </div>
</div>

</body>
</html>
