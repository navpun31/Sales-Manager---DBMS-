<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Vehicles</h2>
<table align="left" border="1">
	<tr>
		<th>Vehicle No</th>
		<th>Type</th>
		<th>Driver Name</th>
		<th>Agent</th>
		<th>Contact(s)</th>
		<th>Actions</th>
	</tr>

	<c:forEach items="${vehicles}" var="vehicle">
		<tr>
			<td><c:out value="${vehicle.vehicleNo}"/></td>
			<td><c:out value="${vehicle.type}"/></td>
			<td><c:out value="${vehicle.driverName}"/></td>
			<td>${vehicle.transportAgent.companyName} (${vehicle.transportAgent.agentID})</td>
			
			<td>
				<c:if test="${!empty vehicle}">
			    <c:if test="${!empty vehicle.contact}">
			    	<c:forEach items="${vehicle.contact}" var="v" varStatus="loop">
				    	<c:if test="${loop.index > 0}">,<br/>
				        </c:if>
				    	${v}
			    	</c:forEach>
		    	</c:if>
			    </c:if>
			</td>
			<td align="center"><a href="${pageContext.request.contextPath}/vehicle/edit?vehicleNo=${vehicle.vehicleNo}">Edit</a> | <a href="${pageContext.request.contextPath}/vehicle/delete?vehicleNo=${vehicle.vehicleNo}">Delete</a></td>
		</tr>
	</c:forEach>
</table>