<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--确认收货</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid">
	<div class="m-confirm-process">
		<img src="/tmall/image/site/comformPayFlow.png" />
		<br />
		<span><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${o.createDate}" /></span>
		<span><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${o.payDate}" /></span>
		<span><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${o.deliveryDate}" /></span>
	</div>
	<div class="m-confirm-title">
		<strong>我已收到货，同意支付宝付款</strong>
	</div>
	<div style="margin: 30px 0;">
		<div>订单信息</div>
		<table border="1" class="m-confirm-detail">
			<thead>
				<tr>
					<th width="60%">宝贝</th>
					<th width="10%">单价</th>
					<th width="7%">数量</th>
					<th width="10%">商品总价</th>
					<th width="13%">运费</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${o.orderItems}" var="oi">
				<tr>
					<td height="120px">
						<a href="foreproduct?pid=${oi.product.id}">
							<img src="/tmall/image/product/${oi.product.id}/${oi.product.firstProductImage.id}.jpg" style="width: 80px; height: 80px; margin-right: 20px; float: left;" />
						</a>
						<a href="foreproduct?pid=${oi.product.id}" style="display: block; margin: 20px; text-align: left;">${oi.product.name}</a>
					</td>
					<td>￥<fmt:formatNumber value="${oi.product.promotePrice}" pattern="#,#00.00#" /></td>
					<td>${oi.number}</td>
					<td style="font-size: 18px;"><strong>￥<fmt:formatNumber value="${oi.product.promotePrice * oi.number}" pattern="#,#00.00#" /></strong></td>
					<td>快递 ： 0.00</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="float: right;">实付款： <span style="color: #FF0036;">￥<fmt:formatNumber value="${o.total}" pattern="#,#00.00#" /></span></div>
	</div>

	<div class="m-confirm-info">
		<div>
			<span style="margin-right: 50px;">订单编号：</span> ${o.orderCode}
			<img src="/tmall/image/site/tmallbuy.png" height="23px" />
		</div>
		<div>
			<span style="margin-right: 50px;">卖家昵称：</span> 天猫商铺
			<span class="m-aLiWangWang-allactive"></span>
		</div>
		<div><span style="margin-right: 50px;">收货信息：</span> <c:out value="${o.address}, ${o.receiver}, ${o.mobile}, ${post}" escapeXml="true" /></div>
		<div><span style="margin-right: 50px;">成交时间：</span> <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${o.createDate}" /></div>
	</div>

	<div class="m-confirm-btn">
		<div class="s-redColor" style="font-size: 18px;">
			<strong>请收到货后，再确认收货！否则您可能钱货两空！</strong>
			<button type="button" class="btn btn-primary s-btn"><a href="foreconfirm?oid=${o.id}" style="color: white;">确认收货</a></button>
		</div>
	</div>
</div>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>