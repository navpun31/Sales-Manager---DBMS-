<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Returns | Credit Note | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	<br/>
	<a href="${pageContext.request.contextPath}/creditNote">Back</a>
	
	<h2>Details</h2>
	<table>
		<tr>
			<td><b>No.:</b></td>
			<td><c:out value="${creditNote.creditNoteNo}"/></td>
		</tr>
		<tr>
			<td><b>Date:</b></td>
			<td><fmt:formatDate value="${creditNote.dateOfReturn}" pattern="dd-MM-yyyy" /></td>
		</tr>
		<tr>
			<td><b>Party:</b></td>
			<td><c:out value="${creditNote.partyID}"/></td>
		</tr>
	</table>
	
	<br/>
	<jsp:include page="creditNoteSummary.jsp" />
	
	<c:if test="${!creditNote.clear}">
		<br/>
		<h2>${action} Credit Note Entry</h2>
		<p class="error">${errorMessage}</p>
		<form:form method="POST" action="${pageContext.request.contextPath}/creditNote/addreturns/${creditNote.creditNoteNo}/add" modelAttribute="commandEntry">
	   		<table>
			    <tr>
			        <td><form:label path="key.creditNoteNo">CreditNote ID:</form:label></td>
			        <td><form:input path="key.creditNoteNo" value="${creditNote.creditNoteNo}" readonly='true' /></td>
			    </tr>
			    <tr>
			    	<td><form:label path="key.invoiceID">Invoice:</form:label></td>
					<td>
						<form:select path="key.invoiceID"  required='true'>
							<c:if test="${!empty invoices}">
					    	<c:forEach items="${invoices}" var="i" varStatus="loop">
					    		<c:choose>
							    	<c:when test="${returnEntry.invoiceID == i.invoiceID}">
							        	<form:option value="${i.invoiceID}" selected='true'>${i.invoiceID}</form:option>
							        </c:when>
							        <c:otherwise>
							        	<form:option value="${i.invoiceID}" >${i.invoiceID}</form:option>
							        </c:otherwise>
						    	</c:choose>
					    	</c:forEach>
						    </c:if>
						</form:select>
					</td>
			    </tr>
			    <tr>
			    	<td><form:label path="key.productID">Product:</form:label></td>
					<td>
						<form:select path="key.productID"  required='true'>
							<c:if test="${!empty products}">
					    	<c:forEach items="${products}" var="p" varStatus="loop">
					    		<c:choose>
							    	<c:when test="${returnEntry.productID == p.productID}">
							        	<form:option value="${p.productID}" selected='true'>${p.productID} (${p.name})</form:option>
							        </c:when>
							        <c:otherwise>
							        	<form:option value="${p.productID}" >${p.productID} (${p.name})</form:option>
							        </c:otherwise>
						    	</c:choose>
					    	</c:forEach>
						    </c:if>
						</form:select>
					</td>
			    </tr>
			    <tr>
			        <td><form:label path="quantity">Quantity:</form:label></td>
			        <td><form:input path="quantity" value="${returnEntry.quantity}" required='true' /></td>
			    </tr>
			    <tr>
			      <td colspan="2"><input type="submit" value="Submit"/></td>
		      	</tr>
			</table> 
		</form:form>
	</c:if>
	
	<br/>
	<jsp:include page="creditNoteEntries.jsp" />
	
</body>
</html>