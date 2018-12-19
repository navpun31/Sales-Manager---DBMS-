<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Base</title>
<style type="text/css">
	body{
		font-family: Arial;		
	}
	table {
	    border-collapse: collapse;     
	}
	table, th, td {
	    border: 1px solid black;
	}
	th {
		height: 25px;	
		background: lightblue;
	}
	th, td {
		padding: 5px;
	}
	.error {
		color: red;
	}
	.success {
		color: green;
	}
	ul {
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	}
	li {
	    float: left;
	}
	li a {
	    display: block;
	    color: black;
	    text-align: center;
	    padding: 14px 16px;
	    text-decoration: none;
	}
	li a:hover {
	    background-color: lightblue;
	}
	li:last-child {
	    border-right: none;
	}
	li a:visited {
		color: black;  
	}
	li a:active {
		color: black;  
	}
	li a:link {
		color: black;  
	}
	a {
		text-decoration: none;
	}
	a:visited {
		color: darkblue;  
	}
	a:active {
		color: darkblue;  
	}
	a:link {
		color: darkblue;  
	}
/* 	input[type=submit] { */
/* 		padding: 5px 20px; */
/* 		background-color:#ddd; */
/* 		border: 0 none; */
/* 		cursor:pointer; */
/* 		border-radius:5px; */
/* 		color: black; */
/* 		border: 1px solid black; */ 
/* 	} */
/* 	input[type=submit]:hover { */
/* 		background-color:#eee; */
/* 	} */
</style>
</head>
<body>
	
	<c:if test="${user != null}">
		<div style="width:100%; height:2px; background-color:darkblue;"></div>
	</c:if>
	<ul>
		<c:choose>
	    	<c:when test="${user == null}">
<!-- 	    		<a href="${pageContext.request.contextPath}/login">Login</a> -->
	        </c:when>
	        <c:otherwise>
	        	<li><a href="${pageContext.request.contextPath}/">Home</a></li>
	
	        	<c:if test="${user != null}">
			   	<c:set var="ADMIN" value="ADMIN"/>
			   	<c:if test="${user.role eq ADMIN}">
			   		<li><a href="${pageContext.request.contextPath}/user">Register</a></li>
				</c:if>
			    </c:if>
	        	
		   		<li><a href="${pageContext.request.contextPath}/user/changepass">Change Password</a></li>
	    		<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	    		<li style="float:right">
					<a href="${pageContext.request.contextPath}/client">
					(<b><c:out value="${user.username}"/></b> | <c:out value="${user.role}"/>)
					</a>
				</li>
	        </c:otherwise>
	   	</c:choose>
   	</ul>
	<c:if test="${user != null}">
		<div style="width:100%; height:2px; background-color:darkblue;"></div>
	</c:if>   	
	
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>