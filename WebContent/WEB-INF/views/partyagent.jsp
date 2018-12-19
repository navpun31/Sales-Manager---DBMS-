<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Party Agent | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	
	<h2>${action} Party Agent</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/partyagent/add">
   		<table>
		    <tr>
		        <td><form:label path="agentID" >Agent ID:</form:label></td>
		        <td><form:input path="agentID" value="${partyAgent.agentID}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="name">Name:</form:label></td>
		        <td><form:input path="name" value="${partyAgent.name}" required='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="perPartyPay">Per Party Pay:</form:label></td>
		        <td><form:input path="perPartyPay" value="${partyAgent.perPartyPay}"/></td>
		    </tr>
		    
		    <c:if test="${!empty partyAgent}">
		    <c:if test="${!empty partyAgent.contact}">
		    	<tr>
			    	<td><form:label path="contact">Contact(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${partyAgent.contact}" var="c" varStatus="loop">
					        <form:input path="contact" value="${c}"/><br/>
				    	</c:forEach>
			    	</td>
		    	</tr>
	    	</c:if>
		    </c:if>
		    
		    <tr>
		        <td><form:label path="contact">New Contact:</form:label></td>
		        <td><form:input path="contact" value=""/></td>
		    </tr>
		    
		    <c:if test="${!empty partyAgent}">
		    <c:if test="${!empty partyAgent.email}">
		    	<tr>
			    	<td><form:label path="email">Email(s):</form:label></td>
			    	<td>
				    	<c:forEach items="${partyAgent.email}" var="e" varStatus="loop">
					        <form:input path="email" value="${e}"/><br/>
				    	</c:forEach>
			    	</td>
		    	</tr>
	    	</c:if>
		    </c:if>
		    
		    <tr>
		        <td><form:label path="email">New Email:</form:label></td>
		        <td><form:input path="email" value=""/></td>
		    </tr>
		    
		    <tr>
		      <td colspan="2"><input type="submit" value="Submit"/></td>
	      	</tr>
		</table> 
	</form:form>
		
		
		
  	<c:if test="${!empty partyAgents}">
		<h2>Party Agents</h2>
		<table align="left" border="1">
			<tr>
				<th>Agent ID</th>
				<th>Name</th>
				<th>Per Party Pay</th>
				<th>Contact(s)</th>
				<th>Email(s)</th>
				<th>Actions</th>
			</tr>
		
			<c:forEach items="${partyAgents}" var="partyAgent">
				<tr>
					<td><c:out value="${partyAgent.agentID}"/></td>
					<td><c:out value="${partyAgent.name}"/></td>
					<td><c:out value="${partyAgent.perPartyPay}"/></td>
					<td>
						<c:if test="${!empty partyAgent}">
					    <c:if test="${!empty partyAgent.contact}">
					    	<c:forEach items="${partyAgent.contact}" var="c" varStatus="loop">
						    	<c:if test="${loop.index > 0}">,<br/>
						        </c:if>
						    	${c}
					    	</c:forEach>
				    	</c:if>
					    </c:if>
					</td>
					<td>
						<c:if test="${!empty partyAgent}">
					    <c:if test="${!empty partyAgent.email}">
					    	<c:forEach items="${partyAgent.email}" var="e" varStatus="loop">
						    	<c:if test="${loop.index > 0}">,<br/>
						        </c:if>
						    	${e}
					    	</c:forEach>
				    	</c:if>
					    </c:if></td>
					<td align="center">
					<a href="${pageContext.request.contextPath}/partyagent/edit?agentID=${partyAgent.agentID}">Edit</a> | 
					<a href="${pageContext.request.contextPath}/partyagent/delete?agentID=${partyAgent.agentID}">Delete</a> | 
					<a href="${pageContext.request.contextPath}/partyagent/parties/${partyAgent.agentID}">Parties</a><br/>
					<a href="${pageContext.request.contextPath}/payment/debit?id=${partyAgent.agentID}&type=PARTYAGENT">Pay</a> |
					<a href="${pageContext.request.contextPath}/payment/summary/debit/PARTYAGENT/${partyAgent.agentID}">Payment Summary</a><br/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
	
</body>
</html>