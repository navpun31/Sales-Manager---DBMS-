<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Credit Notes</h2>
<table>
	<tr>
		<th>Credit Note No.</th>
		<th>Date</th>
		<th>Party</th>
		<th>Quantity</th>
		<th>Amount</th>
		<th>Actions</th>
		<th>Paid</th>
	</tr>

	<c:forEach items="${creditNotes}" var="creditNote">
		<tr>
			<td><c:out value="${creditNote.creditNoteNo}"/></td>
			<td><fmt:formatDate value="${creditNote.dateOfReturn}" pattern="dd-MM-yyyy" /></td>
			<td>${creditNote.party.firmName} (${creditNote.party.gstin})</td>
			<td><c:out value="${creditNote.quantity}"/></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${creditNote.total}"/></td>
			<td align="center">
				<c:if test="${!creditNote.clear}">
					<a href="${pageContext.request.contextPath}/creditNote/edit?creditNoteNo=${creditNote.creditNoteNo}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/creditNote/delete?creditNoteNo=${creditNote.creditNoteNo}">Delete</a> | 
					<a href="${pageContext.request.contextPath}/payment/bills?type=CREDITNOTE&partyID=${creditNote.partyID}">Make Payment</a><br/>
				</c:if>
				
				<a href="${pageContext.request.contextPath}/creditNote/addreturns/${creditNote.creditNoteNo}">Details</a> |
				<a href="${pageContext.request.contextPath}/creditNote/print/${creditNote.creditNoteNo}/original" target="_blank">Original</a> | 
				<a href="${pageContext.request.contextPath}/creditNote/print/${creditNote.creditNoteNo}/duplicate" target="_blank">Duplicate</a><br/>
				
				<c:if test="${creditNote.clear}">
				<a href="${pageContext.request.contextPath}/payment/summary/bills/CREDITNOTE/${creditNote.creditNoteNo}" target="_blank">Payment Summary</a>
				</c:if>
			</td>
			<td align="center">
				<c:choose>
					<c:when test="${creditNote.clear}">
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