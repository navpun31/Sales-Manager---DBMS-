<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Summary</h2>
<table>
	<tr>
		<td><b>SubTotal:</b></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.subtotal}"/></td>
	</tr>
	<tr>
		<td><b>Taxable Value:</b></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.taxableValue}"/></td>
	</tr>
	<tr>
		<td><b>Total:</b></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.total}"/></td>
	</tr>
	<tr>
		<td><b>Rounded Total:</b></td>
		<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.roundedTotal}"/></td>
	</tr>
	<tr>
		<td><b>Quantity:</b></td>
		<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.quantity}"/></td>
	</tr>
</table>