<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--支付成功</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid" style="border: 1px solid #CCCCCC;">
			<div class="m-success-top">
				<span class="glyphicon glyphicon-ok s-ok"></span>
				<strong>您已成功付款</strong>
			</div>
			<div class="m-success-details">
				<ul>
					<li>收货地址： ${o.address}</li>
					<li>实付款：￥ <fmt:formatNumber value="${o.total}" pattern="#,#00.00#" /></li>
					<li>预计<fmt:formatDate type="date" value="${time}" />送达</li>
				</ul>
				<div>
					您可以 <a href="foreorder" class="link">查看已买到的宝贝 </a><a href="" class="link"> 查看交易详情</a>
				</div>
			</div>
			<div class="m-success-underline"></div>
			<div class="m-success-warning">
				<img src="/tmall/image/site/warning.png" alt="warning" />
				<strong> 安全提醒：</strong>下单后，<span style="color: #ff0036;">用QQ给您发送链接办理退款的都是骗子！</span>天猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！
			</div>
		</div>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>