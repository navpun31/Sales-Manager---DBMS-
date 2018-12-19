<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="com.rk.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Stats | Product | ${client.firmName}</title>
	<script type="text/javascript">
	window.onload = function() { 
		var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled: true,
// 			theme: "dark1",
			title: {
				text: "Product Stats"
			},	
			axisX: {
				title: "Product"
			},
			axisY: {
				title: "Quantity"
			},
			data: [
				{
				type: "bar",
				name: "Sold",
				indexLabel: "{y}",
				indexLabelFontColor: "#5A5757",
				indexLabelPlacement: "outside",
				showInLegend: true,
				dataPoints: ${sold},
			},{
				type: "bar",
				name: "Returns",
				indexLabel: "{y}",
				indexLabelFontColor: "#5A5757",
				indexLabelPlacement: "outside",
				showInLegend: true,
				dataPoints: ${ret},
			},{
				type: "bar",
				name: "Stock",
				indexLabel: "{y}",
				indexLabelFontColor: "#5A5757",
				indexLabelPlacement: "outside",
				showInLegend: true,
				dataPoints: ${stock},
			}]
		});
		chart.render();
	}
	</script>
</head>

<body>
	<jsp:include page="base.jsp" />
<!-- 	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script> -->
	<div align="center">
		<div id="chartContainer" style=" width: 60%;"></div>
	</div>
</body>
</html>