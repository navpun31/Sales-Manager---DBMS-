<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Manufacturer | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Manufacturer</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/manufacturer/add">
   		<table>
		    <tr>
		        <td><form:label path="manuID">Manufacturer ID:</form:label></td>
		        <td><form:input path="manuID" value="${manufacturer.manuID}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="name">Name:</form:label></td>
		        <td><form:input path="name" value="${manufacturer.name}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine1">Address Line 1:</form:label></td>
		        <td><form:input path="address.addressLine1" value="${manufacturer.address.addressLine1}"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine2">Address Line 2:</form:label></td>
		        <td><form:input path="address.addressLine2" value="${manufacturer.address.addressLine2}"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.pin">PIN:</form:label></td>
		        <td><form:input path="address.pin" value="${manufacturer.address.pin}"/></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="address.city">City:</form:label></td>
				<td>
					<form:select  path="address.city">
						<c:if test="${!empty cities}">
				    	<c:forEach items="${cities}" var="c" varStatus="loop">
					    	<c:choose>
						    	<c:when test="${manufacturer.address.city == c.city}">
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
		        <td><form:input path="address.stateName" value="${manufacturer.address.stateName}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.stateCode">State Code:</form:label></td>
		        <td><form:input path="address.stateCode" value="${manufacturer.address.stateCode}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.country">Country:</form:label></td>
		        <td><form:input path="address.country" value="${manufacturer.address.country}" readonly='true' /></td>
		    </tr>
		    
		    <c:if test="${!empty manufacturer}">
		    <c:if test="${!empty manufacturer.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${manufacturer.contact}" var="c" varStatus="loop">
					        <form:input path="contact" value="${c}"/><br/>
				    	</c:forEach>
			    	</td>
		    	</tr>
	    	</c:if>
		    </c:if>
		    
		    <tr>
		        <td><form:label path="contact">New Contact:</form:label></td>
		        <td><form:input path="contact" value=""/></td>
		    </tr>
		    
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty manufacturers}">
		<h2>Manufacturers</h2>
		<table align="left" border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>State</th>
				<th>Country</th>
				<th>Contact(s)</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${manufacturers}" var="manufacturer">
				<tr>
					<td><c:out value="${manufacturer.manuID}"/></td>
					<td><c:out value="${manufacturer.name}"/></td>
					<td><c:out value="${manufacturer.address.addressLine1}"/><br/>
						<c:out value="${manufacturer.address.addressLine2}"/><br/>
						<c:out value="${manufacturer.address.city}"/> - <c:out value="${manufacturer.address.pin}"/>
					<td><c:out value="${manufacturer.address.stateName}"/> (<fmt:formatNumber minIntegerDigits="2" value="${manufacturer.address.stateCode}" />)</td>
					<td><c:out value="${manufacturer.address.country}"/></td>
					<td>
						<c:if test="${!empty manufacturer}">
					    <c:if test="${!empty manufacturer.contact}">
					    	<c:forEach items="${manufacturer.contact}" var="c" varStatus="loop">
						    	<c:if test="${loop.index > 0}">,<br/>
						        </c:if>
						    	${c}
					    	</c:forEach>
				    	</c:if>
					    </c:if>
					</td>
					<td align="center">
						<a href="${pageContext.request.contextPath}/manufacturer/edit?manuID=${manufacturer.manuID}">Edit</a> | 
						<a href="${pageContext.request.contextPath}/manufacturer/delete?manuID=${manufacturer.manuID}">Delete</a> |
						<a href="${pageContext.request.contextPath}/payment/debit?id=${manufacturer.manuID}&type=MANUFACTURER">Pay</a><br/>
					<a href="${pageContext.request.contextPath}/payment/summary/debit/MANUFACTURER/${manufacturer.manuID}">Payment Summary</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>