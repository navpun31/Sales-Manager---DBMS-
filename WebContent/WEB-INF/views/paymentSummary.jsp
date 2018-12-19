<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Summary | Payment | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	
	<h2>Payment</h2>
	<table>
		<tr>
			<td><b>Payment ID:</b></td>
			<td><fmt:formatNumber minIntegerDigits="8" value="${payment.paymentID}" /></td>
		</tr>
		<tr>
			<td><b>Date:</b></td>
			<td><fmt:formatDate value="${payment.dateOfPay}" pattern="dd-MM-yyyy" /></td>
		</tr>
		<tr>
			<td><b>Amount:</b></td>
			<td>${payment.amount}</td>
		</tr>
		<tr>
			<td><b>Mode:</b></td>
			<td>${payment.modeOfPay}</td>
		</tr>
		<tr>
			<td><b>Type:</b></td>
			<td>${payment.type}</td>
		</tr>
	</table>
	
</body>
</html>