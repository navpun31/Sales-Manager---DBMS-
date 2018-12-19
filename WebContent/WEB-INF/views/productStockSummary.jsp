<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock | Product | ${client.firmName}</title>
</head>
<body>

	<jsp:include page="base.jsp" />
	
	<h2>Stocks</h2>
	<table>
		<tr>
			<th>Stock ID</th>
			<th>Quantity</th>
			<th>Date</th>
			<th>Manufacturer</th>
		</tr>
		<c:if test="${!empty stocks}">
		<c:forEach items="${stocks}" var="stock">
			<tr>
				<td>${stock.stockID}</td>
				<td>${stock.quantity}</td>
				<td><fmt:formatDate value="${stock.dateOfManu}" pattern="dd-MM-yyyy" /></td>
				<td>${stock.manufacturer.name} (${stock.manufacturer.manuID})</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>

</body>
</html>