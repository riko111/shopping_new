<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ</title>
</head>
<body>
<h1>ようこそ</h1>
<form action="/shopping_new/LoginServlet" method="post">
ユーザー名：<input type="text" name="userName"><br>
パスワード：<input type="password" name="pass"><br>
<button type="submit">ログイン</button><br>
</form>
<a href="RegisterServlet">アカウント登録</a>
</body>
</html>