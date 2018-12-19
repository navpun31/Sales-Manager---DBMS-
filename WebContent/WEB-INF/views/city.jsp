<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>City | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} City</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/city/add">
   		<table>
		    <tr>
		        <td><form:label path="city">City:</form:label></td>
		        <td><form:input path="city" value="${city.city}" required='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="state">State:</form:label></td>
				<td>
					<form:select  path="state" required='true'>
						<c:if test="${!empty states}">
				    	<c:forEach items="${states}" var="s" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${city.state == s.state}">
						        	<form:option value="${s.state}" selected='true'>${s.state} (${s.country})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${s.state}">${s.state} (${s.country})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty cities}">
		<h2>Cities</h2>
		<table align="left" border="1">
			<tr>
				<th>City</th>
				<th>State</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${cities}" var="city">
				<tr>
					<td><c:out value="${city.city}"/></td>
					<td><c:out value="${city.state}"/></td>
					<td align="center"><a href="${pageContext.request.contextPath}/city/edit?city=${city.city}">Edit</a> | <a href="${pageContext.request.contextPath}/city/delete?city=${city.city}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>