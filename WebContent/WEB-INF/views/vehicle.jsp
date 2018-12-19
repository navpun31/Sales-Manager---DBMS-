<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Vehicle | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Vehicle</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/vehicle/add">
   		<table>
		    <tr>
		        <td><form:label path="vehicleNo">Vehicle No:</form:label></td>
		        <td><form:input path="vehicleNo" value="${vehicle.vehicleNo}" required='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="type">Type:</form:label></td>
				<td>
					<form:select path="type">
						<c:choose>
					    	<c:when test="${vehicle.type == 'AUTO'}">
					        	<form:option value="AUTO" selected='true'>Auto</form:option>
		    					<form:option value="TRUCK">Truck</form:option>
					        </c:when>
					        <c:otherwise>
						        <form:option value="AUTO">Auto</form:option>
				    			<form:option value="TRUCK" selected='true'>Truck</form:option>
					        </c:otherwise>
				    	</c:choose>
					</form:select>
				</td>
		    </tr>
		    <tr>
		        <td><form:label path="driverName">Driver Name:</form:label></td>
		        <td><form:input path="driverName" value="${vehicle.driverName}" required='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="agentID">Agent ID:</form:label></td>
				<td>
					<form:select  path="agentID" required='true'>
						<c:if test="${!empty transportAgents}">
				    	<c:forEach items="${transportAgents}" var="ta" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${vehicle.agentID == ta.agentID}">
						        	<form:option value="${ta.agentID}" selected='true'>${ta.agentID} (${ta.companyName})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${ta.agentID}">${ta.agentID} (${ta.companyName})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    
		    <c:if test="${!empty vehicle}">
		    <c:if test="${!empty vehicle.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${vehicle.contact}" var="c" varStatus="loop">
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
		
		
		
  	<c:if test="${!empty vehicles}">
		<jsp:include page="vehicleDetailTable.jsp"/>
	</c:if>
	
	
	
</body>
</html>