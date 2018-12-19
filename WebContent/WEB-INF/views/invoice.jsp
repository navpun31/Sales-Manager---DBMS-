<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Invoice | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Invoice</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/invoice/add">
   		<table>
		    <tr>
		        <td><form:label path="invoiceID">ID:</form:label></td>
		        <td><form:input path="invoiceID" value="${invoice.invoiceID}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="dateOfIssue">Date:</form:label></td>
		        <td><form:input path="dateOfIssue" value="${invoice.dateOfIssue}" required='true' type='date' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="partyID">Party:</form:label></td>
				<td>
					<form:select path="partyID">
						<c:if test="${!empty parties}">
				    	<c:forEach items="${parties}" var="p" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${invoice.partyID == p.gstin}">
						        	<form:option value="${p.gstin}" selected='true'>${p.gstin} (${p.firmName})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${p.gstin}">${p.gstin} (${p.firmName})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    <tr>
		    	<td>Tax:</td>
				<td>
					<form:select path="tax">
				    	<c:forEach items="${taxes}" var="t" varStatus="loop">
				        	<form:option value="${t.taxType}">${t.taxType} (${t.percent}%)</form:option><br/>
				    	</c:forEach>
					</form:select>
				</td>
		    </tr>
		    <tr>
		        <td><form:label path="discount">Discount (%):</form:label></td>
		        <td><form:input path="discount" value="${invoice.discount}" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="freight">Freight:</form:label></td>
		        <td><form:input path="freight" value="${invoice.freight}" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="ewayNo">E-way:</form:label></td>
		        <c:choose>
		        	<c:when test="${invoice.roundedTotal == null || invoice.roundedTotal < 50000}">
		        		<td><form:input path="ewayNo" value="${invoice.ewayNo}" readonly='true' /></td>
		        	</c:when>
		        	<c:otherwise>
		        		<td><form:input path="ewayNo" value="${invoice.ewayNo}" /></td>
		        	</c:otherwise>
		        </c:choose>
		    </tr>
		    <tr>
		        <td><form:label path="lorryReceiptNo">Lorry Receipt:</form:label></td>
		        <td><form:input path="lorryReceiptNo" value="${invoice.lorryReceiptNo}" /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="vehicleID">Vehicle:</form:label></td>
				<td>
					<form:select  path="vehicleID">
						<c:if test="${!empty vehicles}">
				    	<c:forEach items="${vehicles}" var="v" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${invoice.vehicleID == v.vehicleNo}">
						        	<form:option value="${v.vehicleNo}" selected='true'>${v.vehicleNo} (${v.type} | ${v.driverName})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${v.vehicleNo}">${v.vehicleNo} (${v.type} | ${v.driverName})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty invoices}">
		<jsp:include page="invoiceDetailTable.jsp" />
	</c:if>
	
	
	
</body>
</html>