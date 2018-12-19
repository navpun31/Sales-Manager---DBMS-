<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Tax | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Tax</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/tax/add">
   		<table>
		    <tr>
		        <td><form:label path="taxType">Tax:</form:label></td>
		        <td><form:input path="taxType" value="${tax.taxType}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="percent">Percent:</form:label></td>
		        <td><form:input path="percent" value="${tax.percent}" required='true' /></td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty taxes}">
		<h2>Taxes</h2>
		<table>
			<tr>
				<th>Tax Type</th>
				<th>Percent</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${taxes}" var="tax">
				<tr>
					<td><c:out value="${tax.taxType}"/></td>
					<td><c:out value="${tax.percent}"/></td>
					<td align="center"><a href="${pageContext.request.contextPath}/tax/edit?taxType=${tax.taxType}">Edit</a> | <a href="${pageContext.request.contextPath}/tax/delete?taxType=${tax.taxType}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>