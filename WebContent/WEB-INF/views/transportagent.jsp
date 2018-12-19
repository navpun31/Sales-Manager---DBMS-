<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Transport Agent | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	
	<h2>${action} Transport Agent</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/transportagent/add">
   		<table>
		    <tr>
		        <td><form:label path="agentID" >Agent ID:</form:label></td>
		        <td><form:input path="agentID" value="${transportAgent.agentID}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="companyName">Company Name:</form:label></td>
		        <td><form:input path="companyName" value="${transportAgent.companyName}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="perDeliveryPay">Per Delivery Pay:</form:label></td>
		        <td><form:input path="perDeliveryPay" value="${transportAgent.perDeliveryPay}"/></td>
		    </tr>
		    
		    <c:if test="${!empty transportAgent}">
		    <c:if test="${!empty transportAgent.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${transportAgent.contact}" var="c" varStatus="loop">
					        <form:input path="contact" value="${c}"/><br/>
				    	</c:forEach>
			    	</td>
		    	</tr>
	    	</c:if>
		    </c:if>
		    
<%-- 		    <c:if test="${!empty transportAgent}"> --%>
<%-- 		    <c:if test="${!empty transportAgent.contact}"> --%>
<%-- 		    	<c:forEach items="${transportAgent.contact}" var="c" varStatus="loop"> --%>
<!-- 				    <tr> -->
<%-- 				    	<c:choose> --%>
<%-- 					    	<c:when test="${loop.index == 0}"> --%>
<%-- 					        	<td><form:label path="contact">Contact(s):</form:label></td> --%>
<%-- 					        </c:when> --%>
<%-- 					        <c:otherwise> --%>
<%-- 					        	<td><form:label path="contact"></form:label></td> --%>
<%-- 					        </c:otherwise> --%>
<%-- 				    	</c:choose> --%>
<%-- 				        <td><form:input path="contact" value="${c}"/></td> --%>
<!-- 				    </tr> -->
<%-- 		    	</c:forEach> --%>
<%-- 	    	</c:if> --%>
<%-- 		    </c:if> --%>
		    
		    <tr>
		        <td><form:label path="contact">New Contact:</form:label></td>
		        <td><form:input path="contact" value=""/></td>
		    </tr>
		    
		    <c:if test="${!empty transportAgent}">
		    <c:if test="${!empty transportAgent.email}">
		    	<tr>
			    	<td><form:label path="email">Email(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${transportAgent.email}" var="e" varStatus="loop">
					        <form:input path="email" value="${e}"/><br/>
				    	</c:forEach>
			    	</td>
		    	</tr>
	    	</c:if>
		    </c:if>
		    
<%-- 		    <c:if test="${!empty transportAgent}"> --%>
<%-- 		    <c:if test="${!empty transportAgent.email}"> --%>
<%-- 		    	<c:forEach items="${transportAgent.email}" var="e" varStatus="loop"> --%>
<!-- 				    <tr> -->
<%-- 				    	<c:choose> --%>
<%-- 					    	<c:when test="${loop.index == 0}"> --%>
<%-- 					        	<td><form:label path="email">Email(s):</form:label></td> --%>
<%-- 					        </c:when> --%>
<%-- 					        <c:otherwise> --%>
<%-- 					        	<td><form:label path="email"></form:label></td> --%>
<%-- 					        </c:otherwise> --%>
<%-- 				    	</c:choose> --%>
<%-- 				        <td><form:input path="email" value="${e}"/></td> --%>
<!-- 				    </tr> -->
<%-- 		    	</c:forEach> --%>
<%-- 	    	</c:if> --%>
<%-- 		    </c:if> --%>
		    <tr>
		        <td><form:label path="email">New Email:</form:label></td>
		        <td><form:input path="email" value=""/></td>
		    </tr>
		    
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty transportAgents}">
		<h2>Transport Agents</h2>
		<table align="left" border="1">
			<tr>
				<th>Agent ID</th>
				<th>Company Name</th>
				<th>Per Delivery Pay</th>
				<th>Contact(s)</th>
				<th>Email(s)</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${transportAgents}" var="transportAgent">
				<tr>
					<td><c:out value="${transportAgent.agentID}"/></td>
					<td><c:out value="${transportAgent.companyName}"/></td>
					<td><c:out value="${transportAgent.perDeliveryPay}"/></td>
					<td>
						<c:if test="${!empty transportAgent}">
					    <c:if test="${!empty transportAgent.contact}">
					    	<c:forEach items="${transportAgent.contact}" var="c" varStatus="loop">
						    	<c:if test="${loop.index > 0}">,<br/>
						        </c:if>
						    	${c}
					    	</c:forEach>
				    	</c:if>
					    </c:if>
					</td>
					<td>
						<c:if test="${!empty transportAgent}">
					    <c:if test="${!empty transportAgent.email}">
					    	<c:forEach items="${transportAgent.email}" var="e" varStatus="loop">
						    	<c:if test="${loop.index > 0}">,<br/>
						        </c:if>
						    	${e}
					    	</c:forEach>
				    	</c:if>
					    </c:if></td>
					<td align="center">
					<a href="${pageContext.request.contextPath}/transportagent/edit?agentID=${transportAgent.agentID}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/transportagent/delete?agentID=${transportAgent.agentID}">Delete</a> | 
					<a href="${pageContext.request.contextPath}/transportagent/vehicles/${transportAgent.agentID}">Vehicles</a><br/>
					<a href="${pageContext.request.contextPath}/payment/debit?id=${transportAgent.agentID}&type=TRANSPORT">Pay</a> | 
					<a href="${pageContext.request.contextPath}/payment/summary/debit/TRANSPORT/${transportAgent.agentID}">Payment Summary</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>