<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Product</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/product/add">
<%-- 	<form:errors path="*" cssClass="error"/> --%>
   		<table>
		    <tr>
		        <td><form:label path="productID">Product ID:</form:label></td>
		        <td><form:input path="productID" value="${product.productID}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="name">Name:</form:label></td>
		        <td><form:input path="name" value="${product.name}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="description">Description:</form:label></td>
		        <td><form:input path="description" value="${product.description}" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="cost">Cost:</form:label></td>
		        <td><form:input path="cost" value="${product.cost}" required='true' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="categoryHSN">Category:</form:label></td>
				<td>
					<form:select  path="categoryHSN"  required='true'>
						<c:if test="${!empty categories}">
				    	<c:forEach items="${categories}" var="c" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${product.categoryHSN == c.hsn}">
						        	<form:option value="${c.hsn}" selected='true'>${c.hsn} (${c.name})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${c.hsn}">${c.hsn} (${c.name})</form:option>
						        </c:otherwise>
					    	</c:choose>
				    	</c:forEach>
					    </c:if>
					</form:select>
				</td>
		    </tr>
		    <tr>
		    	<td><form:label path="size">Size(s):</form:label></td>
				<td>
					<form:select path="size">
						<c:set var="T" value="true" />
						<c:set var="F" value="false" />
						<c:set var="S" value="S"/>
						<c:set var="M" value="M"/>
						<c:set var="L" value="L"/>
						<c:set var="XL" value="XL"/>
						<c:set var="XXL" value="XXL"/>
						<c:set var="XL3" value="3XL"/>
						
						<c:set var="contains" value="false" />
						<c:forEach var="sz" items="${product.size}"><c:if test="${sz eq S}"><c:set var="contains" value="true" /></c:if>
						</c:forEach>
						<c:choose>
					    	<c:when test="${contains eq T}">
					    	<form:option value="S" selected='true'>S</form:option>
					    	</c:when>
					        <c:otherwise><form:option value="S">S</form:option>
					        </c:otherwise>
				    	</c:choose>
				    	
				    	<c:set var="contains" value="false" />
						<c:forEach var="sz" items="${product.size}"><c:if test="${sz eq M}"><c:set var="contains" value="true" /></c:if>
						</c:forEach>
						<c:choose>
					    	<c:when test="${contains eq T}">
					    	<form:option value="M" selected='true'>M</form:option>
					    	</c:when>
					        <c:otherwise><form:option value="M">M</form:option>
					        </c:otherwise>
				    	</c:choose>
				    	
				    	<c:set var="contains" value="false" />
						<c:forEach var="sz" items="${product.size}"><c:if test="${sz eq L}"><c:set var="contains" value="true" /></c:if>
						</c:forEach>
						<c:choose>
					    	<c:when test="${contains eq T}">
					    	<form:option value="L" selected='true'>L</form:option>
					    	</c:when>
					        <c:otherwise><form:option value="L">L</form:option>
					        </c:otherwise>
				    	</c:choose>
				    	
				    	<c:set var="contains" value="false" />
						<c:forEach var="sz" items="${product.size}"><c:if test="${sz eq XL}"><c:set var="contains" value="true" /></c:if>
						</c:forEach>
						<c:choose>
					    	<c:when test="${contains eq T}">
					    	<form:option value="XL" selected='true'>XL</form:option>
					    	</c:when>
					        <c:otherwise><form:option value="XL">XL</form:option>
					        </c:otherwise>
				    	</c:choose>
				    	
				    	<c:set var="contains" value="false" />
						<c:forEach var="sz" items="${product.size}"><c:if test="${sz eq XXL}"><c:set var="contains" value="true" /></c:if>
						</c:forEach>
						<c:choose>
					    	<c:when test="${contains eq T}">
					    	<form:option value="XXL" selected='true'>XXL</form:option>
					    	</c:when>
					        <c:otherwise><form:option value="XXL">XXL</form:option>
					        </c:otherwise>
				    	</c:choose>
				    	
				    	<c:set var="contains" value="false" />
						<c:forEach var="sz" items="${product.size}"><c:if test="${sz eq XL3}"><c:set var="contains" value="true" /></c:if>
						</c:forEach>
						<c:choose>
					    	<c:when test="${contains eq T}">
					    	<form:option value="3XL" selected='true'>3XL</form:option>
					    	</c:when>
					        <c:otherwise><form:option value="3XL">3XL</form:option>
					        </c:otherwise>
				    	</c:choose>
		    		</form:select>
				</td>
		    </tr>
		    <tr>
		    	<td><form:label path="color">Color(s):</form:label></td>
				<td>
					<form:select  path="color">
						<c:if test="${!empty colors}">
				    	<c:forEach items="${colors}" var="c" varStatus="loop">
			        		<form:option value="${c.colorCode}">${c.colorCode} (${c.colorName})</form:option>
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



  	<c:if test="${!empty products}">
		<jsp:include page="productDetailTable.jsp" />
	</c:if>
	
	
	
</body>
</html>