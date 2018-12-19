<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} User</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/user/add">
   		<table>
		    <tr>
		        <td><form:label path="username">Username:</form:label></td>
		        <td><form:input path="username" value="${useredit.username}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="password">Password:</form:label></td>
		        <td><form:input path="password" value="" required='true' type='password' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="confpassword">Confirm Password:</form:label></td>
		        <td><form:input path="confpassword" value="" required='true' type='password' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="name">Name:</form:label></td>
		        <td><form:input path="name" value="${useredit.name}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="email">Email:</form:label></td>
		        <td><form:input path="email" value="${useredit.email}" required='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="role">Role:</form:label></td>
				<td>
					<form:select path="role">
						<c:choose>
					    	<c:when test="${useredit.role == 'ADMIN'}">
					        	<form:option value="ADMIN" selected='true'>Admin</form:option>
		    					<form:option value="STAFF">Staff</form:option>
					        </c:when>
					        <c:otherwise>
						        <form:option value="ADMIN">Admin</form:option>
				    			<form:option value="STAFF" selected='true'>Staff</form:option>
					        </c:otherwise>
				    	</c:choose>
					</form:select>
				</td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty users}">
		<h2>Users</h2>
		<table align="left" border="1">
			<tr>
				<th>Username</th>
<!-- 				<th>Password</th> -->
				<th>Name</th>
				<th>Email</th>
				<th>Role</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.username}"/></td>
<%-- 					<td><c:out value="${user.password}"/></td> --%>
					<td><c:out value="${user.name}"/></td>
					<td><c:out value="${user.email}"/></td>
					<td><c:out value="${user.role}"/></td>
					<td align="center"><a href="${pageContext.request.contextPath}/user/switchrole?username=${user.username}">Switch</a> | <a href="${pageContext.request.contextPath}/user/delete?username=${user.username}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>