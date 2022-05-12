<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<h1>ようこそ</h1>
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