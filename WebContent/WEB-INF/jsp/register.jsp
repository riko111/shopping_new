<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録｜ショッピング</title>
</head>
<body>
<h1>アカウント登録</h1>
<form action="/shopping_new/RegisterServlet" method="post">
ユーザー名：<input type="text" name="userName"><br>
パスワード：<input type="password" name="pass"><br>
<button type="submit">アカウント登録</button><br>
</form>
<p>${errorMsg}</p>
<a href="/shopping_new">TOPに戻る</a>
</body>
</html>