<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<header>
<a href="LoginServlet?action=logout" onclick="return confirm('ログアウトします。よろしいですか?')">ログアウト</a>
（${loginUser.userName}さん）
</header>

<h1>管理者画面</h1>

<h2>商品リスト</h2>
<table>
<thead>
<tr>
<th>商品名</th><th>価格</th><th>数量</th><th>&nbsp;</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${itemList}">
<tr>
    <td>${item.name}</td>
    <td>¥${item.price}</td>

<%-- ■在庫切れのチェック --%>
<c:choose>
	<%-- ■在庫あり --%>
	<c:when test="${item.quantity > 0}">
    <input type="hidden" name="item_id" value="${item.id}">
    <input type="hidden" name="name" value="${item.name}">
    <input type="hidden" name="price" value="${item.price}">
    <td>${i}</td>
	</c:when>
	<%-- ■在庫なし --%>
	<c:otherwise>
		<td>品切れ中！</td>
	</c:otherwise>
</c:choose>

    <tr><td colspan="4"><hr></td></tr>
</c:forEach>
</tbody>
</table>

<p><a href="AdminServletTest"><button type="button">在庫の初期設定</button></a></p>
<p>${errorMsg}</p>
</body>
</html>