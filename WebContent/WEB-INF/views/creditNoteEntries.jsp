<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${!empty entries}">
	<h2>Entries</h2>
	<table>
		<tr>
			<th>Invoice ID</th>
			<th>Product ID</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Amount</th>
			<th>Tax</th>
			<th>Total</th>
			<c:if test="${!creditNote.clear}"><th>Actions</th></c:if>
		</tr>
	
		<c:forEach items="${entries}" var="entry">
			<tr>
				<td><c:out value="${entry.invoiceID}"/></td>
				<td><c:out value="${entry.productID}"/></td>
				<td><c:out value="${entry.quantity}"/></td>
				<td><c:out value="${entry.price}"/></td>
				<td><c:out value="${entry.amount}"/></td>
				<td>
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${entry.taxAmount}"/> @ 
					<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${entry.tax}"/> %
				</td>
				<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${entry.total}"/></td>
				<c:if test="${!creditNote.clear}">
					<td align="center">
					<a href="${pageContext.request.contextPath}/creditNote/addreturns/${creditNote.creditNoteNo}/edit?invoiceID=${entry.invoiceID}&productID=${entry.productID}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/creditNote/addreturns/${creditNote.creditNoteNo}/delete?invoiceID=${entry.invoiceID}&productID=${entry.productID}">Delete</a> 
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</c:if>