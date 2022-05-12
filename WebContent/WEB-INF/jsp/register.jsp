<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<h1>アカウント登録</h1>
<form action="/shopping_new/RegisterServlet" method="post">
ユーザー名：<input type="text" name="userName" required><br>
パスワード：<input type="password" name="pass" required><br>
※ユーザー名・パスワードとも、半角英数字の組合せで8～15字で入力して下さい。<br>
<button type="submit">アカウント登録</button><br>
</form>
<p>${errorMsg}</p>
<a href="/shopping_new">TOPに戻る</a>
</body>
</html>