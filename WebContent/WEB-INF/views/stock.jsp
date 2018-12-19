<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Stock | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Stock</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/stock/add">
   		<table>
		    <tr>
		        <td><form:label path="stockID">Stock ID:</form:label></td>
		        <td><form:input path="stockID" value="${stock.stockID}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="quantity">Quantity:</form:label></td>
		        <td><form:input path="quantity" value="${stock.quantity}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="dateOfManu">Date:</form:label></td>
		        <td><form:input path="dateOfManu" value="${stock.dateOfManu}" required='true' type='date' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="manuID">Manufacturer:</form:label></td>
		    	<td>
		    	<form:select  path="manuID" required='true'>
					<c:if test="${!empty manufacturers}">
			    	<c:forEach items="${manufacturers}" var="m" varStatus="loop">
			    		<c:choose>
					    	<c:when test="${stock.manuID == m.manuID}">
					        	<form:option value="${m.manuID}" selected='true'>${m.manuID} (${m.name})</form:option>
					        </c:when>
					        <c:otherwise>
					        	<form:option value="${m.manuID}">${m.manuID} (${m.name})</form:option>
					        </c:otherwise>
				    	</c:choose>
			    	</c:forEach>
				    </c:if>
				</form:select>
				</td>
		    </tr>
		    <tr>
		    	<td><form:label path="productID">Product:</form:label></td>
		    	<td>
		    	<form:select  path="productID">
					<c:if test="${!empty products}">
			    	<c:forEach items="${products}" var="p" varStatus="loop">
			    		<c:choose>
					    	<c:when test="${stock.productID == p.productID}">
					        	<form:option value="${p.productID}" selected='true'>${p.productID} (${p.name})</form:option>
					        </c:when>
					        <c:otherwise>
					        	<form:option value="${p.productID}">${p.productID} (${p.name})</form:option>
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
		
		
		
  	<c:if test="${!empty stocks}">
		<h2>Stocks</h2>
		<table>
			<tr>
				<th>Stock ID</th>
				<th>Quantity</th>
				<th>Date</th>
				<th>Manufacturer</th>
				<th>Product</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${stocks}" var="stock">
				<tr>
					<td><c:out value="${stock.stockID}"/></td>
					<td><c:out value="${stock.quantity}"/></td>
					<td><fmt:formatDate value="${stock.dateOfManu}" pattern="dd-MM-yyyy" /></td>
					<td>${stock.manufacturer.name} (${stock.manuID})</td>
					<td>${stock.product.name} (${stock.productID})</td>
					<td align="center"><a href="${pageContext.request.contextPath}/stock/edit?stockID=${stock.stockID}">Edit</a> | <a href="${pageContext.request.contextPath}/stock/delete?stockID=${stock.stockID}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>