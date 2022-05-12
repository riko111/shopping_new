<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品リスト｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<h1>商品リスト</h1>

<table>
<thead>
<tr>
<th>商品名</th><th>価格</th><th>数量</th><th>&nbsp;</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${itemList}">
<tr>
<form action="/shopping_new/CartServlet" method="post">
    <td>${item.name}</td>
    <td>¥${item.price}</td>

<%-- ■在庫切れのチェック --%>
<c:choose>
	<%-- ■在庫あり --%>
	<c:when test="${item.quantity > 0}">
    <input type="hidden" name="item_id" value="${item.id}">
    <input type="hidden" name="name" value="${item.name}">
    <input type="hidden" name="price" value="${item.price}">
    <td><select name="quantity">
    <c:forEach var="i" begin="1" end="${item.quantity}" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
		</select></td>
    <td><button type="submit">カートに入れる</button></td>
	</c:when>
	<%-- ■在庫なし --%>
	<c:otherwise>
		<td>品切れ中！</td>
	</c:otherwise>
</c:choose>

</form>
    <tr><td colspan="4"><hr></td></tr>
</c:forEach>
</tbody>
</table>


</body>
</html>