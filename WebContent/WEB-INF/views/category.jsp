<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Category | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Product Category</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/category/add">
   		<table>
		    <tr>
		        <td><form:label path="hsn">HSN:</form:label></td>
		        <td><form:input path="hsn" value="${category.hsn}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="name">Name:</form:label></td>
		        <td><form:input path="name" value="${category.name}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="description">Description:</form:label></td>
		        <td><form:input path="description" value="${category.description}" /></td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty categories}">
		<h2>Product Categories</h2>
		<table>
			<tr>
				<th>HSN</th>
				<th>Name</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${categories}" var="category">
				<tr>
					<td><c:out value="${category.hsn}"/></td>
					<td><c:out value="${category.name}"/></td>
					<td><c:out value="${category.description}"/></td>
					<td align="center">
					<a href="${pageContext.request.contextPath}/category/edit?hsn=${category.hsn}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/category/delete?hsn=${category.hsn}">Delete</a> |
					<a href="${pageContext.request.contextPath}/category/products/${category.hsn}">Products</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>