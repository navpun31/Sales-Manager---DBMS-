<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Warehouse | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Warehouse</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/warehouse/add">
   		<table>
		    <tr>
		        <td><form:label path="warehouseID">ID:</form:label></td>
		        <td><form:input path="warehouseID" value="${warehouse.warehouseID}" readonly='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="categoryHSN">Category:</form:label></td>
				<td>
					<form:select  path="categoryHSN" required='true'>
						<c:if test="${!empty categories}">
				    	<c:forEach items="${categories}" var="c" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${warehouse.categoryHSN == c.hsn}">
						        	<form:option value="${c.hsn}" selected='true'>${c.hsn} (${c.name})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${c.hsn}">${c.hsn} (${c.name})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine1">Address Line 1:</form:label></td>
		        <td><form:input path="address.addressLine1" value="${warehouse.address.addressLine1}"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.addressLine2">Address Line 2:</form:label></td>
		        <td><form:input path="address.addressLine2" value="${warehouse.address.addressLine2}"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.pin">PIN:</form:label></td>
		        <td><form:input path="address.pin" value="${warehouse.address.pin}"/></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="address.city">City:</form:label></td>
				<td>
					<form:select  path="address.city">
						<c:if test="${!empty cities}">
				    	<c:forEach items="${cities}" var="c" varStatus="loop">
					    	<c:choose>
						    	<c:when test="${warehouse.address.city == c.city}">
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
		        <td><form:input path="address.stateName" value="${warehouse.address.stateName}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.stateCode">State Code:</form:label></td>
		        <td><form:input path="address.stateCode" value="${warehouse.address.stateCode}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="address.country">Country:</form:label></td>
		        <td><form:input path="address.country" value="${warehouse.address.country}" readonly='true' /></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="managerID">Manager:</form:label></td>
				<td>
					<form:select  path="managerID">
						<c:choose>
					    	<c:when test="${!empty party.agentID}">
					    		<form:option value="-1">-</form:option>
					        </c:when>
					        <c:otherwise>
					        	<form:option value="-1" selected='true'>-</form:option>
					        </c:otherwise>
				    	</c:choose>
				    	
						<c:if test="${!empty employees}">
				    	<c:forEach items="${employees}" var="e" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${warehouse.managerID == e.empID}">
						        	<form:option value="${e.empID}" selected='true'>${e.empID} (${e.name})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${e.empID}">${e.empID} (${e.name})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    
		    <c:if test="${!empty warehouse}">
		    <c:if test="${!empty warehouse.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${warehouse.contact}" var="c" varStatus="loop">
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
		
		
  	<c:if test="${!empty warehouses}">
		<h2>Warehouses</h2>
		<table align="left" border="1">
			<tr>
				<th>ID</th>
				<th>Category</th>
				<th>HSN</th>
				<th>Address</th>
				<th>State</th>
				<th>Country</th>
				<th>Manager</th>
				<th>Contact(s)</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${warehouses}" var="warehouse">
			<c:if test="${!empty warehouse}">
				<tr>
					<td><c:out value="${warehouse.warehouseID}"/></td>
					<td><c:out value="${warehouse.category.name}"/></td>
					<td><c:out value="${warehouse.category.hsn}"/></td>
					<td><c:out value="${warehouse.address.addressLine1}"/><br/>
						<c:out value="${warehouse.address.addressLine2}"/><br/>
						<c:out value="${warehouse.address.city}"/> - <c:out value="${warehouse.address.pin}"/>
					<td><c:out value="${warehouse.address.stateName}"/> (<fmt:formatNumber minIntegerDigits="2" value="${warehouse.address.stateCode}" />)</td>
					<td><c:out value="${warehouse.address.country}"/></td>
					<td>
						<c:choose>
					    	<c:when test="${!empty warehouse.managerID}">
					    		${warehouse.manager.name} (${warehouse.manager.empID})
					        </c:when>
					        <c:otherwise>
					        	-
					        </c:otherwise>
				    	</c:choose>
					</td>
					<td>
					    <c:if test="${!empty warehouse.contact}">
					    	<c:forEach items="${warehouse.contact}" var="c" varStatus="loop">
						    	<c:if test="${loop.index > 0}">,<br/>
						        </c:if>
						    	${c}
					    	</c:forEach>
				    	</c:if>
					</td>
					<td align="center">
					<a href="${pageContext.request.contextPath}/warehouse/edit?warehouseID=${warehouse.warehouseID}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/warehouse/delete?warehouseID=${warehouse.warehouseID}">Delete</a> |
					<a href="${pageContext.request.contextPath}/warehouse/employees/${warehouse.warehouseID}">Employees</a>
					</td>
				</tr>
			</c:if>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>