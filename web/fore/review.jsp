<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--评论</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>
<c:forEach items="${o.orderItems}" var="oi">
<div class="g-mid m-review-item">
	<div class="pic">
		<img src="/tmall/image/product/${oi.product.id}/${oi.product.firstProductImage.id}.jpg"/>
	</div>
	<div class="item-info">
		<div class="title">${oi.product.name}</div>
		<div><span class="head">价格:</span> <strong class="s-redColor" style="font-size: 18px;">￥<fmt:formatNumber value="${oi.product.promotePrice * oi.number}" pattern="#,#00.00#" /> </strong>元</div>
		<div><span class="head">配送:</span> 快递: 0.00</div>
		<div><span class="head">月销量:</span> <strong class="s-redColor">${oi.product.saleCount}</strong>件</div>
	</div>
</div>
</c:forEach>
<div class="g-mid" style="background-color: #E3E3E3; padding: 20px 50px;">
	其他买家，需要你的建议哦！
	<form action="forereview?oid=${o.id}" method="post" enctype="multipart/form-data">
		<textarea name="review" rows="6" cols="60" class="text"></textarea>
		<button type="submit" class="btn btn-info">发表评论</button>
	</form>
</div>


<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>