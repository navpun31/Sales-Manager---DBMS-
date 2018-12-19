<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Payment | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Payment</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/payment/bills">
   		<table>
		    <tr>
		        <td><form:label path="dateOfPay">Date:</form:label></td>
		        <td><form:input path="dateOfPay" value="" required='true' type='date' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="amount">Amount:</form:label></td>
		        <td><form:input path="amount" value="0" readonly='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="modeOfPay">Mode:</form:label></td>
				<td>
					<form:select path="modeOfPay">
			        <form:option value="CASH">Cash</form:option>
   					<form:option value="CARD">Card</form:option>
					</form:select>
				</td>
		    </tr>
		    
		    <c:choose>
		    	<c:when test="${type == 'INVOICE'}">
			      	<tr>
				    	<td><label>Invoices:</label></td>
						<td>
							<select name="ids" multiple required>
								<c:if test="${!empty invoices}">
						    	<c:forEach items="${invoices}" var="i" varStatus="loop">
						        	<option value="${i.invoiceID}">${i.invoiceID}</option>
						    	</c:forEach>
							    </c:if>
							</select>
						</td>
				    </tr>
			    </c:when>
			    <c:otherwise>
		    		<tr>
				    	<td><label>Credit Notes:</label></td>
						<td>
							<select name="ids" multiple required>
								<c:if test="${!empty creditNotes}">
						    	<c:forEach items="${creditNotes}" var="c" varStatus="loop">
						        	<option value="${c.creditNoteNo}">${c.creditNoteNo}</option>
						    	</c:forEach>
							    </c:if>
							</select>
						</td>
				    </tr>
			    </c:otherwise>
		    </c:choose>
		    
		    <tr>
		    	<td><form:label path="type">Type:</form:label></td>
				<td><form:input path="type" value="${type}" readonly='true' /></td>
		    </tr>
		    <tr>
				<td colspan="2">
					<input type="submit" value="Submit" onclick="return confirm('Payment once saved CAN NOT be editted/deleted.\nAre you sure you want to add the payment?')" />
				</td>
	      	</tr>
		</table> 
	</form:form>
	
	
</body>
</html>