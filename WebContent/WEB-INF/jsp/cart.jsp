<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.LinkedHashMap, java.util.List, java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String cartId = (String) session.getAttribute("cartId");
Map<Integer, List<Object>> cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);

session.setAttribute("cartMap", cartMap);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート｜ショッピング</title>
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
<c:forEach var="item" items="${cartMap}">
<form action="/shopping_new/CartServlet?action=delete" method="post">
<tr>
    <td>${item.value[0]}</td>
    <td>${item.value[1]}</td>
    <td>${item.value[2]}</td>
    <td>${item.value[3]}</td>
    <td><button type="submit" name="item_id" value="${item.key}">削除</button></td>
<tr>
</form>
</c:forEach>
</tbody>
</table>

<button type="submit" name="${item.key}">注文確定</button>
<c:if test="${empty cartMap}"> <%-- 要素数が0のインスタンス --%>
<p>お客様のカートに商品はありません。</p>
</c:if>

<a href="LoginServlet">商品リストに戻る</a>
</body>
</html>