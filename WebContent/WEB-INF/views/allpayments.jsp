<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payments | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	
	<h2>Payments</h2>
	<table>
		<tr>
			<th>Payment</th>
			<th>Date</th>
			<th>Amount</th>
			<th>Mode</th>
			<th>Option</th>
			<th>Type</th>
		</tr>
		<c:if test="${!empty payments}">
		<c:forEach items="${payments}" var="payment">
			<tr>
				<td><fmt:formatNumber minIntegerDigits="8" value="${payment[0]}" /></td>
				<td><fmt:formatDate value="${payment[1]}" pattern="dd-MM-yyyy" /></td>
				<td>${payment[2]}</td>
				<td>${payment[3]}</td>
				<td>${payment[4]}</td>
				<td>${payment[5]}</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
	


</body>
</html>