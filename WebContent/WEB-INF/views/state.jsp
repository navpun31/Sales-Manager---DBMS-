<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>State | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} State</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/state/add">
   		<table>
		    <tr>
		        <td><form:label path="state">State:</form:label></td>
		        <td><form:input path="state" value="${state.state}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="stateCode">State Code:</form:label></td>
		        <td><form:input path="stateCode" value="${state.stateCode}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="country">Country:</form:label></td>
		        <td><form:input path="country" value="${state.country}" required='true' /></td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty states}">
		<h2>States</h2>
		<table>
			<tr>
				<th>State</th>
				<th>Code</th>
				<th>Country</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${states}" var="state">
				<tr>
					<td><c:out value="${state.state}"/></td>
					<td><fmt:formatNumber minIntegerDigits="2" value="${state.stateCode}" /></td>
					<td><c:out value="${state.country}"/></td>
					<td align="center"><a href="${pageContext.request.contextPath}/state/edit?state=${state.state}">Edit</a> | <a href="${pageContext.request.contextPath}/state/delete?state=${state.state}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>