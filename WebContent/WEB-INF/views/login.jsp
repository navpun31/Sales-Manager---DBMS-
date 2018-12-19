<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	
	<div width=100% align="center">
		<h2>Login</h2>
		<p class="error">${errorMessage}</p>
		<form method="POST" action="${pageContext.request.contextPath}/login">
	   		<table>
			    <tr>
			        <td><label>Username:</label></td>
			        <td><input name='username' required/></td>
			    </tr>
			    <tr>
			        <td><label>Password:</label></td>
			        <td><input name='password' required type='password'/></td>
			    </tr>
			    <tr align="center">
			      <td colspan="2"><input type="submit" value="Submit"/></td>
		      	</tr>
			</table> 
		</form>
	</div>
	
</body>
</html>