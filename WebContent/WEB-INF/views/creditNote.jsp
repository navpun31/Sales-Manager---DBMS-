<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Credit Note | ${client.firmName}</title>
</head>
<body>
	<jsp:include page="base.jsp" />

	<h2>${action} Credit Note</h2>
	<p class="error">${errorMessage}</p>
	<form:form method="POST" action="${pageContext.request.contextPath}/creditNote/add">
   		<table>
		    <tr>
		        <td><form:label path="creditNoteNo">Credit Note No:</form:label></td>
		        <td><form:input path="creditNoteNo" value="${creditNote.creditNoteNo}" readonly='true' /></td>
		    </tr>
		    <tr>
		        <td><form:label path="dateOfReturn">Date:</form:label></td>
		        <td><form:input path="dateOfReturn" value="${creditNote.dateOfReturn}" required='true' type='date' /></td>
		    </tr>
		    <tr>
		    	<td><form:label path="partyID">Party:</form:label></td>
				<td>
					<form:select path="partyID">
						<c:if test="${!empty parties}">
				    	<c:forEach items="${parties}" var="p" varStatus="loop">
				    		<c:choose>
						    	<c:when test="${creditNote.partyID == p.gstin}">
						        	<form:option value="${p.gstin}" selected='true'>${p.gstin} (${p.firmName})</form:option>
						        </c:when>
						        <c:otherwise>
						        	<form:option value="${p.gstin}">${p.gstin} (${p.firmName})</form:option>
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
		
		
  	<c:if test="${!empty creditNotes}">
		<jsp:include page="creditNoteDetailTable.jsp"/>
	</c:if>
	
	
	
</body>
</html>