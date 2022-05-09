<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<title>注文履歴｜ショッピング</title>
</head>
<body>
<h1>注文履歴</h1>

<table>
<thead>
<tr>
<th>注文日時</th><th>商品名</th><th>単価</th><th>数量</th><th>合計(商品ごと)</th>
</tr>
</thead>
<tbody>
<c:forEach var="history" items="${historyList}">
<tr>
    <td><c:out value="${history.order_date}" /></td>
    <td><c:out value="${history.item_name}" /></td>
    <td><c:out value="${history.item_price}" /></td>
    <td><c:out value="${history.order_num}" /></td>
    <td><c:out value="${history.sum_price}" /></td>
<tr>
</c:forEach>
</tbody>
</table>

<a href="LoginServlet">商品リストに戻る</a>
</body>
</html>

<%--
	<td><c:out value="${history.id}" /></td>
	<td><c:out value="${history.user_id}" /></td>
	<td><c:out value="${history.item_id}" /></td>
	<td><c:out value="${history.item_price}" /></td>
	<td><c:out value="${history.order_num}" /></td>
	<td><c:out value="${history.order_date}" /></td>
--%>