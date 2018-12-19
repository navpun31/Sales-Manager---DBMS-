<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${client.firmName} | Sales Manager</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	<div align="center">
	<h1><u>${client.firmName} Administration Portal</u></h1>
	
	<table width=60% style="padding:20px 20px;">
		<tr align = "left">
			<th><h2>Party</h2>
			</th>
			<th><h2>Products</h2>
			</th>
			<th><h2>Sales</h2>
			</th>
			<th><h2>Employees</h2>
			</th>
		</tr>
		<tr>
			<td valign="top" width=25%>
			<a href="${pageContext.request.contextPath}/party">Party</a><br/><br/>
			<a href="${pageContext.request.contextPath}/partyagent">Party Agent</a><br/><br/>
			<a href="${pageContext.request.contextPath}/vehicle">Vehicle</a><br/><br/>
			<a href="${pageContext.request.contextPath}/transportagent">Transport Agent</a><br/><br/>
			<a href="${pageContext.request.contextPath}/city">City</a><br/><br/>
			<a href="${pageContext.request.contextPath}/state">State</a><br/><br/>
			</td>
			
			<td valign="top" width=25%>
			<a href="${pageContext.request.contextPath}/category">Category</a><br/><br/>
			<a href="${pageContext.request.contextPath}/product">Product</a><br/><br/>
			<a href="${pageContext.request.contextPath}/color">Color</a><br/><br/>	
			<a href="${pageContext.request.contextPath}/stock">Stock</a><br/><br/>
			<a href="${pageContext.request.contextPath}/stats/product">Product Stats</a>
			</td>
			
			<td valign="top" width=25%>
			<a href="${pageContext.request.contextPath}/tax">Tax</a><br/><br/>
			<a href="${pageContext.request.contextPath}/invoice">Invoice</a><br/><br/>
			<a href="${pageContext.request.contextPath}/creditNote">Credit Note</a><br/><br/>
			<a href="${pageContext.request.contextPath}/payment">Payments</a><br/><br/>
			<a href="${pageContext.request.contextPath}/stats/profit">Profit Stats</a>
			</td>
			
			<td valign="top" width=25%>
			<a href="${pageContext.request.contextPath}/manufacturer">Manufacturer</a><br/><br/>
			<a href="${pageContext.request.contextPath}/warehouse">Warehouse</a><br/><br/>
			<a href="${pageContext.request.contextPath}/employee">Employee</a><br/><br/>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>