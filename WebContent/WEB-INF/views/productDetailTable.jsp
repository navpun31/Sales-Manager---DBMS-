<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Products</h2>
<table border="1">
	<tr>
		<th>Product ID</th>
		<th>Name</th>
		<th>Description</th>
		<th>Cost</th>
		<th>Category</th>
		<th>HSN</th>
		<th>Size(s)</th>
		<th>Color(s)</th>
		<th>Image(s)</th>
		<th>Add Image</th>
		<th>Actions</th>
	</tr>

	<c:forEach items="${products}" var="product">
		<tr>
			<td><c:out value="${product.productID}"/></td>
			<td><c:out value="${product.name}"/></td>
			<td><c:out value="${product.description}"/></td>
			<td><c:out value="${product.cost}"/></td>
			<td><c:out value="${product.category.name}"/></td>
			<td><c:out value="${product.category.hsn}"/></td>
			<td>
				<c:if test="${!empty product}">
			    <c:if test="${!empty product.size}">
			    	<c:forEach items="${product.size}" var="s" varStatus="loop">
				    	<c:if test="${loop.index > 0}">,</c:if>
				    	${s}
			    	</c:forEach>
		    	</c:if>
			    </c:if>
		    </td>
		    <td>
				<c:if test="${!empty product}">
			    <c:if test="${!empty product.color}">
			    	<c:forEach items="${product.color}" var="c" varStatus="loop">
				    	<c:if test="${loop.index > 0}">,<br/></c:if>
				    	${c}
			    	</c:forEach>
		    	</c:if>
			    </c:if>
		    </td>
		    
		    <td>
			    <c:if test="${!empty product.image}">
					<table>
						<tr>
							<th>ID</th>
							<th>Filename</th>
							<th>Actions</th>
						</tr>
						<c:forEach items="${product.image}" var="image">
							<tr>
								<td>${image.imageID}</td>
								<td>${image.filename}</td>
								<td>
									<a href="${pageContext.request.contextPath}/product/image/download/${image.imageID}" target="_blank">View</a> |
									<a href="${pageContext.request.contextPath}/product/image/remove/${image.imageID}"
										onclick="return confirm('Are you sure you want to delete this image?')">Delete</a> 
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
		    </td>
		    
		    <td>
			    <form:form method="post" action="${pageContext.request.contextPath}/product/${product.productID}/image/save" commandName="image" enctype="multipart/form-data">
					<input type="file" name="file" required></input>
					<input type="submit" value="Add"/>
				</form:form>
		    </td>
			<td align="center">
			<a href="${pageContext.request.contextPath}/product/edit?productID=${product.productID}">Edit</a> | 
			<a href="${pageContext.request.contextPath}/product/delete?productID=${product.productID}">Delete</a> |
			<a href="${pageContext.request.contextPath}/stock/product/${product.productID}">Stock</a> 
			</td>
		</tr>
	</c:forEach>
</table>