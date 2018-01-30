<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--支付</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid">
	<img src="/tmall/image/site/simpleLogo.png" />
	<div class="g-pay-info">
		<div class="tips">扫一扫付款（元）</div>
		<div class="s-redColor price">￥${o.total}</div>
		<img src="/tmall/image/site/qrOfPay.png" width="200px" />
		<br />
		<button type="button" class="btn btn-info pay-btn"><a href="forepay?oid=${o.id}" style="color: white;">确认支付</a></button>
	</div>
</div>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>