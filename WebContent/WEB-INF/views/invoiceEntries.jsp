<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${!empty entries}">
	<h2>Entries</h2>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Amount</th>
			<c:if test="${!invoice.clear}"><th>Actions</th></c:if>
		</tr>
	
		<c:forEach items="${entries}" var="entry">
			<tr>
				<td><c:out value="${entry.productID}"/></td>
				<td><c:out value="${entry.quantity}"/></td>
				<td><c:out value="${entry.price}"/></td>
				<td><c:out value="${entry.amount}"/></td>
				<c:if test="${!invoice.clear}">
					<td align="center">
					<a href="${pageContext.request.contextPath}/invoice/addentries/${invoice.invoiceID}/edit?productID=${entry.productID}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/invoice/addentries/${invoice.invoiceID}/delete?productID=${entry.productID}">Delete</a> 
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</c:if>