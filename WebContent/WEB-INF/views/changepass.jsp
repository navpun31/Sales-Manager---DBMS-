<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Change Password | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>Change Password</h2>
	<p class="error">${errorMessage}</p>
	<p class="success">${successMessage}</p>
	<form method="POST" action="${pageContext.request.contextPath}/user/changepass">
   		<table>
		    <tr>
		        <td><label>Current Password:</label></td>
		        <td><input name='oldPassword' required type='password'/></td>
		    </tr>
		    <tr>
		        <td><label>New Password:</label></td>
		        <td><input name='newPassword' required type='password'/></td>
		    </tr>
		    <tr>
		        <td><label>Confirm New Password:</label></td>
		        <td><input name='confNewPassword' required type='password'/></td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form>
	
</body>
</html>