<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Entries | Invoice | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	<br/>
	<a href="${pageContext.request.contextPath}/invoice">Back</a>
	
	<h2>Details</h2>
	<table>
		<tr>
			<td><b>ID:</b></td>
			<td><c:out value="${invoice.invoiceID}"/></td>
		</tr>
		<tr>
			<td><b>Date:</b></td>
			<td><fmt:formatDate value="${invoice.dateOfIssue}" pattern="dd-MM-yyyy" /></td>
		</tr>
		<tr>
			<td><b>Party:</b></td>
			<td><c:out value="${invoice.partyID}"/></td>
		</tr>
		<tr>
			<td><b>Taxes:</b></td>
			<td>
				<c:forEach items="${invoice.tax}" var="t" varStatus="loop">
					<c:out value="${t}"/><br/>
		    	</c:forEach>
			</td>
		</tr>
		<tr>
			<td><b>Discount:</b></td>
			<td><c:out value="${invoice.discount}"/></td>
		</tr>
		<tr>
			<td><b>Freight:</b></td>
			<td><c:out value="${invoice.freight}"/></td>
		</tr>
		<tr>
			<td><b>E-way:</b></td>
			<td><c:out value="${invoice.ewayNo}"/></td>
		</tr>
		<tr>
			<td><b>Lorry Receipt:</b></td>
			<td><c:out value="${invoice.lorryReceiptNo}"/></td>
		</tr>
		<tr>
			<td><b>Vehicle:</b></td>
			<td><c:out value="${invoice.vehicleID}"/></td>
		</tr>
	</table>
	
	<br/>
	<jsp:include page="invoiceSummary.jsp" />
	
	<c:if test="${!invoice.clear}">
		<br/>
		<a href="${pageContext.request.contextPath}/party/ratechart/${invoice.partyID}" target="_blank">Rate Chart</a>
		<br/>
		<h2>${action} Invoice Entry</h2>
		<p class="error">${errorMessage}</p>
		<form:form method="POST" action="${pageContext.request.contextPath}/invoice/addentries/${invoice.invoiceID}/add" modelAttribute="commandEntry">
	   		<table>
			    <tr>
			        <td><form:label path="key.invoiceID">Invoice ID:</form:label></td>
			        <td><form:input path="key.invoiceID" value="${invoice.invoiceID}" readonly='true' /></td>
			    </tr>
			    <tr>
			    	<td><form:label path="key.productID">Product:</form:label></td>
					<td>
						<form:select path="key.productID"  required='true'>
							<c:if test="${!empty products}">
					    	<c:forEach items="${products}" var="p" varStatus="loop">
					    		<c:choose>
							    	<c:when test="${invoiceEntry.productID == p.productID}">
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
			        <td><form:input path="quantity" value="${invoiceEntry.quantity}" required='true' /></td>
			    </tr>
			    <tr>
			        <td><form:label path="price">Price:</form:label></td>
			        <td><form:input path="price" value="${invoiceEntry.price}" required='true' /></td>
			    </tr>
			    <tr>
			      <td colspan="2"><input type="submit" value="Submit"/></td>
		      	</tr>
			</table> 
		</form:form>
	</c:if>
	<br/>
	<jsp:include page="invoiceEntries.jsp" />
	
</body>
</html>