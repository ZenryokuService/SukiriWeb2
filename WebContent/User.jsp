<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="sukiri.data.LoginData" %>
<%@ page import="sukiri.data.WebConst" %>

<%
	LoginData data = (LoginData) session.getAttribute(WebConst.USER_DATA_SESSION);
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
  <p>～ユーザーページ～</p>
</div>

<div class="container" >
  <!-- Top row -->
  <div id="status_view" class="row">
    <div class="col-sm-4 border border-dark">
      <h3><%= data.getName() %></h3>
      <p>Lv: <%= data.getLevel() %></p>
      <p>HP: <%= data.getHp() %></p>
      <p>MP: <%= data.getMp() %></p>
    </div>
    <div class="col-sm-4 border border-dark">
      <h3>Player 2</h3>
      <p>Lv: </p>
      <p>HP: </p>
      <p>MP: </p>
    </div>
    <div class="col-sm-4 border border-dark">
      <h3>Player 3</h3>
      <p>Lv: </p>
      <p>HP: </p>
      <p>MP: </p>
    </div>
  </div>
  <hr/>
  <!-- Middle row -->
  <div class="row" style="height: 300px">
    <!-- MessageView -->
    <div id="message_view" class="col-centered col-sm-12">
      <textarea class="col-sm-8" style="height: 70%">プロコンクエストのトップ画面、ここからStartボタン、Continueボタンで始める
      </textarea>
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
        <label><button type="button" onclick="loginMode();" value="start" class="btn btn-primary" disabled="ture">Start</button></label>
        <label><button type="submit" value="continue" class="btn btn-success" disabled="ture">Continue</button></label>
      </form>
    </div>
  </div>
</div>

</body>
</html>
