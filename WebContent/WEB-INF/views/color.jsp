<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Color | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Color</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/color/add">
   		<table>
		    <tr>
		        <td><form:label path="colorCode">Code:</form:label></td>
		        <td><form:input path="colorCode" value="${color.colorCode}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="colorName">Name:</form:label></td>
		        <td><form:input path="colorName" value="${color.colorName}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="red">R:</form:label></td>
		        <td><form:input path="red" value="${color.red}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="green">G:</form:label></td>
		        <td><form:input path="green" value="${color.green}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="blue">B:</form:label></td>
		        <td><form:input path="blue" value="${color.blue}" required='true' /></td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty colors}">
		<h2>Colors</h2>
		<table>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>R</th>
				<th>G</th>
				<th>B</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${colors}" var="color">
				<tr>
					<td><c:out value="${color.colorCode}"/></td>
					<td><c:out value="${color.colorName}"/></td>
					<td><c:out value="${color.red}"/></td>
					<td><c:out value="${color.green}"/></td>
					<td><c:out value="${color.blue}"/></td>
					<td align="center">
					<a href="${pageContext.request.contextPath}/color/edit?colorCode=${color.colorCode}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/color/delete?colorCode=${color.colorCode}">Delete</a> |
					<a href="${pageContext.request.contextPath}/color/products/${color.colorCode}">Products</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>