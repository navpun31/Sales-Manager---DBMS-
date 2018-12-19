<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Party | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Party</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/party/add">
   		<table>
		    <tr>
		        <td><form:label path="gstin">GSTIN:</form:label></td>
		        <td><form:input path="gstin" value="${party.gstin}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="firmName">Firm Name:</form:label></td>
		        <td><form:input path="firmName" value="${party.firmName}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine1">Address Line 1:</form:label></td>
		        <td><form:input path="address.addressLine1" value="${party.address.addressLine1}"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine2">Address Line 2:</form:label></td>
		        <td><form:input path="address.addressLine2" value="${party.address.addressLine2}"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.pin">PIN:</form:label></td>
		        <td><form:input path="address.pin" value="${party.address.pin}"/></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="address.city">City:</form:label></td>
				<td>
					<form:select  path="address.city">
						<c:if test="${!empty cities}">
				    	<c:forEach items="${cities}" var="c" varStatus="loop">
					    	<c:choose>
						    	<c:when test="${party.address.city == c.city}">
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
		        <td><form:input path="address.stateName" value="${party.address.stateName}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.stateCode">State Code:</form:label></td>
		        <td><form:input path="address.stateCode" value="${party.address.stateCode}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.country">Country:</form:label></td>
		        <td><form:input path="address.country" value="${party.address.country}" readonly='true' /></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="agentID">Agent ID:</form:label></td>
				<td>
					<form:select  path="agentID">
						<c:choose>
					    	<c:when test="${!empty party.agentID}">
					    		<form:option value="-1">-</form:option>
					        </c:when>
					        <c:otherwise>
					        	<form:option value="-1" selected='true'>-</form:option>
					        </c:otherwise>
				    	</c:choose>
						
						<c:if test="${!empty partyAgents}">
				    	<c:forEach items="${partyAgents}" var="pa" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${party.agentID == pa.agentID}">
						        	<form:option value="${pa.agentID}" selected='true'>${pa.agentID} (${pa.name})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${pa.agentID}">${pa.agentID} (${pa.name})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    
		    <c:if test="${!empty party}">
		    <c:if test="${!empty party.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${party.contact}" var="c" varStatus="loop">
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
		    
		    <c:if test="${!empty party}">
		    <c:if test="${!empty party.email}">
		    	<tr>
			    	<td><form:label path="email">Email(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${party.email}" var="e" varStatus="loop">
					        <form:input path="email" value="${e}"/><br/>
				    	</c:forEach>
			    	</td>
		    	</tr>
	    	</c:if>
		    </c:if>
		    <tr>
		        <td><form:label path="email">New Email:</form:label></td>
		        <td><form:input path="email" value=""/></td>
		    </tr>
		    
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
  	<c:if test="${!empty parties}">
		<jsp:include page="partyDetailTable.jsp"/>
	</c:if>
	
	
	
</body>
</html>