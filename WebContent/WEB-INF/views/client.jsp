<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Client | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	
	<c:choose>
		<c:when test="${client.firmName != null}">
			<h2>${client.firmName}</h2>
		</c:when>
		<c:otherwise>
			<h2>The Firm</h2>
		</c:otherwise>
	</c:choose>
	
	<p class="error">${errorMessage}</p>
	<p class="success">${successMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/client/add">
   		<table>
		    <tr>
		        <td><form:label path="gstin">GSTIN:</form:label></td>
		        <td><form:input path="gstin" value="${client.gstin}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="firmName">Firm Name:</form:label></td>
		        <td><form:input path="firmName" value="${client.firmName}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine1">Address Line 1:</form:label></td>
		        <td><form:input path="address.addressLine1" value="${client.address.addressLine1}" required='true'/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine2">Address Line 2:</form:label></td>
		        <td><form:input path="address.addressLine2" value="${client.address.addressLine2}" required='true'/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.pin">PIN:</form:label></td>
		        <td><form:input path="address.pin" value="${client.address.pin}" required='true'/></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="address.city">City:</form:label></td>
				<td>
					<form:select  path="address.city" required='true'>
						<c:if test="${!empty cities}">
				    	<c:forEach items="${cities}" var="c" varStatus="loop">
					    	<c:choose>
						    	<c:when test="${client.address.city == c.city}">
						        	<form:option value="${c.city}" selected='true'>${c.city} (${c.state})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${c.city}">${c.city} (${c.state})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>

		    <tr>
		        <td><form:label path="address.stateName">State:</form:label></td>
		        <td><form:input path="address.stateName" value="${client.address.stateName}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.stateCode">State Code:</form:label></td>
		        <td><form:input path="address.stateCode" value="${client.address.stateCode}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.country">Country:</form:label></td>
		        <td><form:input path="address.country" value="${client.address.country}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="landline">Land-line:</form:label></td>
		        <td><form:input path="landline" value="${client.landline}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="mobile">Mobile:</form:label></td>
		        <td><form:input path="mobile" value="${client.mobile}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="email">Email:</form:label></td>
		        <td><form:input path="email" value="${client.email}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="bankName">Bank Name:</form:label></td>
		        <td><form:input path="bankName" value="${client.bankName}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="bankBranch">Branch:</form:label></td>
		        <td><form:input path="bankBranch" value="${client.bankBranch}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="bankAccountNo">A/C No.:</form:label></td>
		        <td><form:input path="bankAccountNo" value="${client.bankAccountNo}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="ifsc">IFSC:</form:label></td>
		        <td><form:input path="ifsc" value="${client.ifsc}" required='true' /></td>
		    </tr>
		    <tr>
		    	<td colspan=2><input type="submit" value="Submit"/></td>
		    </tr>
		</table> 
	</form:form>
		
</body>
</html>