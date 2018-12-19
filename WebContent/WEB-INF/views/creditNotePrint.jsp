<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Print | Credit Note | ${client.firmName}</title>

<style type="text/css">
	@page {
        size: "A4";
        margin: 0.5cm 0.7cm;
    }
    
/*	THE BILL */
	tr.creditNote > th{
		border: 2px solid black;
		padding: 5px 10px;
		background: lightgrey;
	}
	tr.creditNote > td{
		border: 2px solid black;
		padding: 3px 7px;
	}
	.total{
		background: lightgrey;
	}
</style>

<style type="text/css" media="print">
	.printbutton {
		visibility: hidden;
		display: none;
	}
</style>

</head>
<body>




<div class="print" style = "font-family: Arial; margin: auto; font-size: 14; max-width: 700px;">
	<c:if test="${type == 'duplicate'}">
		<style>
			#background{
			    position: absolute;
			    max-width:1000px;
			    right: 0;
			    left: 0;
			    margin: auto;
				text-align: center;
				transform: translateY(135%);
				z-index:-1;
			    /*border: 2px solid black;*/
			}

			#bg-text{
			    color:#E3E3E3;
			    font-size: 90px;
			    font-family: Arial;
			}
		</style>
		<div id="background">
			<p id="bg-text">Duplicate</p>
		</div>
	</c:if>
	
	<c:choose>
		<c:when test="${type == 'original'}">
			<header style="text-align: right;">
				<i><b>
					(Original for Recipient)
				</b></i>
			</header>
		</c:when>
		<c:otherwise>
<!-- 			<header contenteditable='true' style="text-align: right;"> -->
			<header style="text-align: right;">
				<i><b>
					(Duplicate)
				</b></i>
			</header>
		</c:otherwise>
	</c:choose>
	
	<br/>
	<!-- THE FIRM -->
	<table style="padding:0px 0px; margin: 0px 0px; width:100%;">
		<tr style="width: 100%;">
			<td valign="center" style="width:40%; font-size: 45; border-collapse:collapse; padding: 0px 0px; margin: 0px 0x;">
				${firmName}
			</td>
			<td valign="bottom" style="width:20%; font-size:15;">
				<center>
				<strong>
				<u>
					CREDIT NOTE
				</u>
				</strong>
				</center>
				</span>
			</td>
			<td valign="bottom" style="text-align:right; width: 40%; font-size: 12;">
				<strong>
					GSTIN : ${client.gstin}
				</strong>
				<br/>
				${client.address.addressLine1}<br/>
				${client.address.addressLine2}, ${client.address.city} - ${client.address.pin}<br/>
				${client.address.stateName} (<fmt:formatNumber minIntegerDigits="2" value="${client.address.stateCode}" />)
				<br/>
				<strong>
					Ph : ${client.landline} &nbsp; Mob: ${client.mobile}<br/>
					Email : ${client.email}
				</strong>
			</td>
		</tr>
	</table>


<!-- RECEIVER PARTY DETAILS -->
	<br/>
	<table style="width: 100%; padding:10px 20px; border:2px solid black; border-collapse:collapse;">
		<tr>
			<td style="width:70%; border: 2px solid black">
				<!-- Party Details -->
				<table style="width:100%; font-size:13; padding:5px 10px;">
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								Name:
							</strong>
						</td>
						<td colspan=3 style="width: 80%; text-align: left;">
							${party.firmName}
						</td>
					</tr>
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								Address:
							</strong>
						</td>
						<td colspan=3 style="width: 80%; text-align: left;">
							${party.addressLine1}<br/>
							${party.addressLine2}<br/>
							${party.city} - ${party.pin}
						</td>
					</tr>
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								State:
							</strong>
						</td>
						<td style="width: 30%; text-align: left; vertical-align: top;">
							${state.state}
						</td>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								State Code:
							</strong>
						</td>
						<td style="width: 10%; text-align: left; vertical-align: top;">
							<fmt:formatNumber minIntegerDigits="2" value="${state.stateCode}" />
						</td>
					</tr>
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								Country:
							</strong>
						</td>
						<td colspan=3 style="width: 80%; text-align: left;">
							${state.country}
						</td>
					</tr>
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								Contact:
							</strong>
						</td>
						<td colspan=3 style="width: 80%; text-align: left;">
						    <c:if test="${!empty contact}">
						    	<c:forEach items="${contact}" var="c" varStatus="loop">
							    	<c:if test="${loop.index > 0}"><br/>
							        </c:if>
							    	${c}
						    	</c:forEach>
					    	</c:if>
						</td>
					</tr>
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								Email:
							</strong>
						</td>
						<td colspan=3 style="width: 80%; text-align: left;">
						    <c:if test="${!empty email}">
						    	<c:forEach items="${email}" var="e" varStatus="loop">
							    	<c:if test="${loop.index > 0}"><br/>
							        </c:if>
							    	${e}
						    	</c:forEach>
					    	</c:if>
						</td>
					</tr>
					<tr>
						<td style="width: 20%; vertical-align: top;">
							<strong>
								GSTIN:
							</strong>
						</td>
						<td colspan=3 style="width: 80%; text-align: left;">
							${party.gstin}
						</td>
					</tr>
				</table>
			</td>
			
			
			
			<td valign="top" style="border: 2px solid black">
				<table style="width:100%; font-size:13; padding:5px 10px;">
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								Date:
							</strong>
						</td>
						<td style="width: 70%; text-align: left;">
							${creditNote.dateOfReturn}
						</td>
					</tr>
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								No.:
							</strong>
						</td>
						<td style="width: 70%; text-align: left; font-size: 17;">
								<strong>
									${creditNote.creditNoteNo}
								</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>



	<!-- THE BILL -->
	<table style="font-size:14; border:2px solid black; border-collapse:collapse; width: 100%; padding:0px 0px; margin : 0px 0px; ">
		<tr class="creditNote">
			<th style="width: 5%;">S.No.</th>
			<th style="width: 5%;">Inv.</th>
			<th style="width: 50%;">Description of Products</th>
			<th style="width: 5%;">HSN</th>
			<th style="width: 7%;">Qty.</th>
			<th style="width: 7%;">Price</th>
			<th style="width: 7%;">Amount</th>
			<th style="width: 7%;">Tax</th>
			<th style="width: 7%;">Total</th>
		</tr>
		
		<c:set var="filled" value="0" />
		<c:forEach items="${entries}" var="item" varStatus="loop">
			<tr class="creditNote">
				<td style="text-align: center;">
					${loop.index+1}.
					<c:set var="filled" value="${loop.index+1}" />
				</td>
				<td style="text-align: center;">${item.invoiceID}</td>
				<td style="">${item.product.name}</td>
				<td style="text-align: center;">${item.product.categoryHSN}</td>
				<td style="text-align: center;">${item.quantity}</td>
				<td style="text-align: center;">${item.price}</td>
				<td style="text-align: center;">${item.amount}</td>
				<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${item.tax}"/>%</td>
				<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${item.total}"/></td>
			</tr>
		</c:forEach>

		<c:forEach begin="${filled+1}" end="15" varStatus="loop">
			<tr class="creditNote">
				<td style="text-align: center;">${loop.index}.</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
		
		<tr class="creditNote total" style="text-align: center;">
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong>${creditNote.quantity}</strong></td>
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong><fmt:formatNumber type="number" maxFractionDigits="0" value="${creditNote.total}"/></strong></td>
		</tr>
		<tr class="creditNote" style="text-align: center;">
			<td colspan=6></td>
			<td class = "total" colspan="2" style="text-align: right;"><strong>Grand Total</strong></td>
			<td class = "total" colspan=1 style="text-align: center;"><strong>${creditNote.roundedTotal}</strong></td>
		</tr>
	</table>



	<!-- PRINT BUTTON -->
	<div style="width: 100%; text-align: center;" class="printbutton">
		<br/>
		<input type="button" onclick="window.print();" class="printbutton" value="Print" />
	</div>
</div>

	

	
</body>
</html>