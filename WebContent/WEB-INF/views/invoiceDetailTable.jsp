<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Invoices</h2>
<table>
	<tr>
		<th>ID</th>
		<th>Date</th>
		<th>Party</th>
		<th>Taxes</th>
		<th>E-way</th>
		<th>Lorry Receipt</th>
		<th>Vehicle</th>
		<th>Discount</th>
		<th>Freight</th>
		<th>Quantity</th>
		<th>Sub Total</th>
		<th>Taxable Value</th>
		<th>Total</th>
		<th>Rounded Total</th>
		<th>Profit</th>
		<th>Actions</th>
		<th>Paid</th>
	</tr>
	
	<c:forEach items="${invoices}" var="invoice">
	<tr>
		<td><c:out value="${invoice.invoiceID}"/></td>
		<td><fmt:formatDate value="${invoice.dateOfIssue}" pattern="dd-MM-yyyy" /></td>
		<td>${invoice.party.firmName} (${invoice.party.gstin})</td>
		<td>
			<c:forEach items="${invoice.tax}" var="t" varStatus="loop">
				<c:out value="${t}"/><br/>
	    	</c:forEach>
		</td>
		<td><c:out value="${invoice.ewayNo}"/></td>
		<td><c:out value="${invoice.lorryReceiptNo}"/></td>
		<td>${invoice.vehicleID} (${invoice.vehicle.driverName})</td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.discount}"/></td>
		<td><c:out value="${invoice.freight}"/></td>
		<td><c:out value="${invoice.quantity}"/></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.subtotal}"/></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.taxableValue}"/></td>
		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.total}"/></td>
		<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.roundedTotal}"/></td>
		<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.profit}"/></td>
		<td align="center">
			<c:if test="${!invoice.clear}">
				<a href="${pageContext.request.contextPath}/invoice/edit?invoiceID=${invoice.invoiceID}">Edit</a> | 
				<a href="${pageContext.request.contextPath}/invoice/delete?invoiceID=${invoice.invoiceID}">Delete</a> | 
				<a href="${pageContext.request.contextPath}/payment/bills?type=INVOICE&partyID=${invoice.partyID}">Make Payment</a><br/>
			</c:if>
			<a href="${pageContext.request.contextPath}/invoice/addentries/${invoice.invoiceID}">Details</a> |
			<a href="${pageContext.request.contextPath}/invoice/print/${invoice.invoiceID}/original" target="_blank">Original</a> | 
			<a href="${pageContext.request.contextPath}/invoice/print/${invoice.invoiceID}/duplicate" target="_blank">Duplicate</a> <br/>
			
			<c:if test="${invoice.clear}">
			<a href="${pageContext.request.contextPath}/payment/summary/bills/INVOICE/${invoice.invoiceID}" target="_blank">Payment Summary</a>
			</c:if>
		</td>
		<td align="center">
			<c:choose>
				<c:when test="${invoice.clear}">
					<span class="success">Y</span>
				</c:when>
				<c:otherwise>
					<span class="error">N</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</c:forEach>
</table>