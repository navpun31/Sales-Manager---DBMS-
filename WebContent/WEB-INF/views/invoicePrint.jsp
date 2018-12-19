<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Print | Invoice | ${client.firmName}</title>

<style type="text/css">
	@page {
        size: "A4";
        margin: 0.5cm 0.7cm;
    }
    
/*	THE BILL */
	tr.invoice > th{
		border: 2px solid black;
		padding: 0px 10px;
		background: lightgrey;
	}
	tr.invoice > td{
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
					GST INVOICE
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
			<td style="width:50%; border: 2px solid black">
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
								Invoice No.:
							</strong>
						</td>
						<td style="width: 30%; text-align: left; font-size: 17;">
								<strong>
									${invoice.invoiceID}
								</strong>
						</td>
						<td style="width: 40%; vertical-align: top; text-align: right;">
							<strong>
								Date:
							</strong>
							${invoice.dateOfIssue}
						</td>
					</tr>
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								E-Way Bill:
							</strong>
						</td>
						<td colspan=2 style="width: 70%; text-align: left;">
							${invoice.ewayNo}
						</td>
					</tr>
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								Through:
							</strong>
						</td>
						<td colspan=2 style="width: 70%; text-align: left;">
							${transport.companyName}
						</td>
					</tr>
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								Vehicle No.:
							</strong>
						</td>
						<td colspan=2 style="width: 70%; text-align: left;">
							${invoice.vehicleID}
						</td>
					</tr>
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<strong>
								L.R. No.:
							</strong>
						</td>
						<td colspan=2 style="width: 70%; text-align: left;">
							${invoice.lorryReceiptNo}
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>



	<!-- THE BILL -->
	<table style="font-size:14; border:2px solid black; border-collapse:collapse; width: 100%; padding:0px 0px; margin : 0px 0px; ">
		<tr class="invoice">
			<th rowspan=2 style="width: 5%;">S.No.</th>
			<th rowspan=2 style="width: 55%;">Desciption of Products</th>
			<th rowspan=2 style="width: 10%;">HSN</th>
			<th rowspan=2 style="width: 10%;">Qty.</th>
			<th rowspan=2 style="width: 10%;">Rate</th>
			<th colspan=2 style="width: 10%;">Amount</th>
		</tr>
		<tr class="invoice">
			<th style="width: 90%; text-align: left;">Rs.</th>
			<th style="width: 10%;">Ps.</th>
		</tr>
		
		<c:set var="filled" value="0" />
		<c:forEach items="${entries}" var="item" varStatus="loop">
			<tr class="invoice">
				<td style="text-align: center;">
				${loop.index+1}.
				<c:set var="filled" value="${loop.index+1}" />
				</td>
				<td style="">${item.product.name}</td>
				<td style="text-align: center;">${item.product.categoryHSN}</td>
				<td style="text-align: center;">${item.quantity}</td>
				<td style="text-align: center;">${item.price}</td>
				<td style="text-align: center;">${item.amount}</td>
				<td style="text-align: center;">0</td>
			</tr>
		</c:forEach>

		<c:forEach begin="${filled+1}" end="15" varStatus="loop">
			<tr class="invoice">
				<td style="text-align: center;">${loop.index}.</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
		
		<tr class="invoice total" style="text-align: center;">
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong>-</strong></td>
			<td><strong>${invoice.quantity}</strong></td>
			<td><strong>-</strong></td>
			<td><strong><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.subtotal}"/></strong></td>
			<td><strong>0</strong></td>
		</tr>

		<tr class="invoice total">
			<td colspan="3" rowspan=2 style="background: white; font-size: 13;">
				<strong>
				Bank Details: ${client.bankName}, ${client.bankBranch}<br/>
				Current Account A/C NO.: ${client.bankAccountNo} - IFSC: ${client.ifsc}
				</strong>
			</td>
			<td colspan="2" style="text-align: right;"><strong>Sub Total</strong></td>
			<td style="text-align: center;"><strong><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.subtotal}"/></strong></td>
			<td style="text-align: center;"><strong>0</strong></td>
		</tr>
		<tr class="invoice">
			<td colspan="2" style="text-align: right;">Discount
			@ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoice.discount}"/>%</td>
			<td style="text-align: center;">(-) <fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.discountAmount}"/></td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(invoice.discountAmount*100)%100}" /></td>
		</tr>
		<tr class="invoice">
<%-- 			<td colspan="3" valign="top" rowspan='${span}' style="background:None; padding:0px;margin:0px;"> --%>
<!-- 				<table style="width:100%; padding:5px 5px; font-size:13; height: 100%;"> -->
<!-- 					<tr> -->
<!-- 						<td colspan=2 style="text-align: justify;"> -->
<!-- 							<strong> -->
<!-- 								Total Invoice Amount (in Words): -->
<!-- 							</strong> -->
<%-- 							${invoice.totalWords} --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td colspan=2 style="text-align: justify;"> -->
<!-- 							<strong> -->
<!-- 								Amount of Tax subject to Reverse Charges: -->
<!-- 							</strong> -->
<%-- 							${invoice.taxWords} --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</td> -->
			<td colspan="3" rowspan="${span+3}" style="width:100%; height:100%; padding: 5px 10px; font-size: 13;">
				<div style="overflow: auto; float: left; height: 100%; width:70%;">
					<strong>
						E. and O.E.<br/>
						All subject to Jaipur Jurisdiction.<br/>
						Goods once sold will NOT be taken back.				
					</strong>
				</div>
				<div style="position: relative; display: inline-block; text-align: center; overflow: auto; float: left; height: 100%; width:30%; ">
					<div style="position: absolute; bottom: 0; left:33%;">
						<strong>
							Signature
						</strong>
					</div>
				</div>
			</td>

			<td colspan="2" style="text-align: right; font-size: 12;">Packing and Forwarding Charges</td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.freight}"/></td>
			<td style="text-align: center;">0</td>
		</tr>
		<tr class="invoice total">
			<td colspan="2" style="text-align: right;"><strong>Taxable Value</strong></td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.taxableValue}"/></td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(invoice.taxableValue*100)%100}" /></td>
		</tr>
		
		<c:forEach items="${tax}" var="tax" varStatus="loop">
		    <tr class="invoice">
			<td colspan="2" style="text-align: right;">${tax.taxType} @ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${tax.percent}"/>%</td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.taxableValue*tax.percent/100}"/></td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(invoice.taxableValue*tax.percent)%100}" /></td>
		</tr>
		</c:forEach>
		<tr class="invoice">
<!-- 			<td colspan="3" rowspan=3 style="width:100%; height:100%; padding: 5px 10px; font-size: 13;"> -->
<!-- 				<div style="overflow: auto; float: left; height: 100%; width:70%;"> -->
<!-- 					<strong> -->
<!-- 						E. and O.E.<br/> -->
<!-- 						All subject to Jaipur Jurisdiction.<br/> -->
<!-- 						Goods once sold will NOT be taken back.				 -->
<!-- 					</strong> -->
<!-- 				</div> -->
<!-- 				<div style="position: relative; display: inline-block; text-align: center; overflow: auto; float: left; height: 100%; width:30%; "> -->
<!-- 					<div style="position: absolute; bottom: 0; left:33%;"> -->
<!-- 						<strong> -->
<!-- 							Signature -->
<!-- 						</strong> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</td> -->
			<td colspan="2" style="text-align: right;">Total</td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${invoice.total}"/></td>
			<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(invoice.total*100)%100}" /></td>
		</tr>
		<tr class="invoice">
			<td colspan="2" style="text-align: right;">Round Off</td>
			<c:choose>
				<c:when test="${((invoice.roundedTotal - invoice.total)*100)%100  >=  0}">
					<td style="text-align: center;">(-) 0</td>
					<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${((invoice.roundedTotal - invoice.total) * 100)%100}" /></td>
				</c:when>
				<c:otherwise>
					<td style="text-align: center;">(-) 0</td>
					<td style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(-1 * (invoice.roundedTotal - invoice.total) * 100)%100}" /></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr class="invoice total">
			<td colspan="2" style="text-align: right;"><strong>Grand Total</strong></td>
			<td colspan="2" style="text-align: center;"><strong>${invoice.roundedTotal}</strong></td>
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