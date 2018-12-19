<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Summary</h2>
<table>
	<tr>
		<td><b>Quantity:</b></td>
		<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${creditNote.quantity}"/></td>
	</tr>
	<tr>
		<td><b>Total:</b></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${creditNote.total}"/></td>
	</tr>
</table>