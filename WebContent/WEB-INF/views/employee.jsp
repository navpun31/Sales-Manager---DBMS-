<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employee | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Employee</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/employee/add">
   		<table>
		    <tr>
		        <td><form:label path="empID">ID:</form:label></td>
		        <td><form:input path="empID" value="${employee.empID}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="name">Name:</form:label></td>
		        <td><form:input path="name" value="${employee.name}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="salary">Salary:</form:label></td>
		        <td><form:input path="salary" value="${employee.salary}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="joinDate">Join Date:</form:label></td>
		        <td><form:input path="joinDate" value="${employee.joinDate}" required='true' type='date' /></td>
		    </tr>
		    
		    <tr>
		    	<td><form:label path="worksIn">Warehouse:</form:label></td>
				<td>
					<form:select  path="worksIn" required='true'>
						<c:if test="${!empty warehouses}">
				    	<c:forEach items="${warehouses}" var="w" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${employee.worksIn == w.warehouseID}">
						        	<form:option value="${w.warehouseID}" selected='true'>${w.warehouseID}</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${w.warehouseID}">${w.warehouseID}</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    
		    <c:if test="${!empty employee}">
		    <c:if test="${!empty employee.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${employee.contact}" var="c" varStatus="loop">
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
		
		
  	<c:if test="${!empty employees}">
		<jsp:include page="employeeDetailTable.jsp"/>
	</c:if>
	
	
	
</body>
</html>