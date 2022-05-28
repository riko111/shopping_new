<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.LinkedHashMap, java.util.List, java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String cartId = (String)session.getAttribute("cartId");
Map<Integer, List<Object>> cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);

session.setAttribute("cartMap", cartMap);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.7">
<title>カート｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<h1>カート</h1>

<table>
<thead>
<tr>
<th>商品名</th><th>価格</th><th>数量</th><th>小計</th><th>&nbsp;</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${cartMap}"> <%-- EL式で使いやすくするため"cartMap"という属性名を用意している --%>
<form action="/shopping_new/CartServlet?action=delete" method="post">
<tr>
    <td>${item.value[0]}</td>
    <td>${item.value[1]}</td>
    <td>${item.value[2]}</td>
    <td>¥${item.value[3]}</td>

    <c:set var="total" value="${total + item.value[3]}" /> <%-- EL式内で加算し合計金額を算出 --%>

    <input type="hidden" name="name" value="${item.value[0]}">
    <td><button type="submit" name="item_id" value="${item.key}">削除</button></td>
<tr>
</form>
</c:forEach>
</tbody>
</table>

		<tr><td colspan="4"><hr></td></tr>
<p>${errorMsg}</p>

<c:choose>
	<c:when test="${empty cartMap}"> <%-- 要素がない --%>
		<p>お客様のカートに商品はありません。</p>
	</c:when>
	<c:otherwise>
		<p>合計金額：¥${total}</p>
	<p><a href="OrderServlet"><button type="button">注文確定</button></a></p>
	</c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>