<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Parties</h2>
<table align="left" border="1">
	<tr>
		<th>GSTIN</th>
		<th>Firm Name</th>
		<th>Address</th>
		<th>State</th>
		<th>Country</th>
		<th>Agent</th>
		<th>Contact(s)</th>
		<th>Email(s)</th>
		<th>Actions</th>
	</tr>

	<c:forEach items="${parties}" var="party">
		<tr>
			<td><c:out value="${party.gstin}"/></td>
			<td><c:out value="${party.firmName}"/></td>
			<td><c:out value="${party.address.addressLine1}"/><br/>
				<c:out value="${party.address.addressLine2}"/><br/>
				<c:out value="${party.address.city}"/> - <c:out value="${party.address.pin}"/>
			<td><c:out value="${party.address.stateName}"/> (<fmt:formatNumber minIntegerDigits="2" value="${party.address.stateCode}" />)</td>
			<td><c:out value="${party.address.country}"/></td>
			<td>
				<c:choose>
			    	<c:when test="${!empty party.agentID}">
			    		${party.partyAgent.name} (${party.partyAgent.agentID})
			        </c:when>
			        <c:otherwise>
			        	-
			        </c:otherwise>
		    	</c:choose>
			</td>
			<td>
				<c:if test="${!empty party}">
			    <c:if test="${!empty party.contact}">
			    	<c:forEach items="${party.contact}" var="c" varStatus="loop">
				    	<c:if test="${loop.index > 0}">,<br/>
				        </c:if>
				    	${c}
			    	</c:forEach>
		    	</c:if>
			    </c:if>
			</td>
			<td>
				<c:if test="${!empty party}">
			    <c:if test="${!empty party.email}">
			    	<c:forEach items="${party.email}" var="e" varStatus="loop">
				    	<c:if test="${loop.index > 0}">,<br/>
				        </c:if>
				    	${e}
			    	</c:forEach>
		    	</c:if>
			    </c:if>
		   	</td>
			<td align="center">
			<a href="${pageContext.request.contextPath}/party/edit?gstin=${party.gstin}">Edit</a> | 
			<a href="${pageContext.request.contextPath}/party/delete?gstin=${party.gstin}">Delete</a> |
			<a href="${pageContext.request.contextPath}/party/ratechart/${party.gstin}">Rate Chart</a> |
			<a href="${pageContext.request.contextPath}/party/bills/${party.gstin}">Bills</a></td>
		</tr>
	</c:forEach>
</table>