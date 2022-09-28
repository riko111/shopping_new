<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="架空のショッピングサイトです">
<meta name="viewport" content="width=device-width, initial-scale=0.7"> <!-- initial-scaleは1.0推奨 -->
<title>ようこそ｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<h1>ショッピングサイトへようこそ！</h1>
<form action="/shopping_new/LoginServlet" method="post">
ユーザー名：<input type="text" name="userName" value="${loginUser.userName}" required><br>
パスワード：<input type="password" name="pass" required><br>
<button type="submit">ログイン</button><br>
</form>
<%-- <p>${errorMsg}</p> ログアウト時にリダイレクトするためメッセージは表示不可--%>
<br>
<a href="RegisterServlet">アカウント登録</a>
</body>
</html>

<!--
ローカルで実行
http://localhost:8091/shopping_new

サーバー（AWS）で実行
http://ec2-15-152-40-23.ap-northeast-3.compute.amazonaws.com:8080/shopping_new
 -->