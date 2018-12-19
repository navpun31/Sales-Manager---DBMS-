<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Employees</h2>
<table>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Salary</th>
		<th>Join Date</th>
		<th>Warehouse</th>
		<th>Contact(s)</th>
		<th>Actions</th>
	</tr>

	<c:forEach items="${employees}" var="employee">
		<tr>
			<td><c:out value="${employee.empID}"/></td>
			<td><c:out value="${employee.name}"/></td>
			<td><c:out value="${employee.salary}"/></td>
			<td><fmt:formatDate value="${employee.joinDate}" pattern="dd-MM-yyyy" /></td>
			<td><c:out value="${employee.worksIn}"/></td>
			<td>
			    <c:if test="${!empty employee.contact}">
			    	<c:forEach items="${employee.contact}" var="c" varStatus="loop">
				    	<c:if test="${loop.index > 0}">,<br/>
				        </c:if>
				    	${c}
			    	</c:forEach>
		    	</c:if>
			</td>
			<td align="center">
			<a href="${pageContext.request.contextPath}/employee/edit?empID=${employee.empID}">Edit</a> | 
			<a href="${pageContext.request.contextPath}/employee/delete?empID=${employee.empID}">Delete</a> | 
			<a href="${pageContext.request.contextPath}/payment/debit?id=${employee.empID}&type=EMPLOYEE">Pay</a><br/>
			<a href="${pageContext.request.contextPath}/payment/summary/debit/EMPLOYEE/${employee.empID}">Payment Summary</a>
			</td>
		</tr>
	</c:forEach>
</table>