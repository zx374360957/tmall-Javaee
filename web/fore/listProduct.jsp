<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>
<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search-min.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<title>天猫tmall.com--${p.name}</title>

<div class="g-mid">
	<a href="forecategory?cid=${p.category.id}">
		<img src="image/category/${p.category.id}.jpg" alt="title" />
	</a>
</div>

<div class="g-mid" style="height: 550px;">
	<div class="m-product-pic">
		<div id="fullpic" class="fullpic">
			<c:forEach items="${p.productSingleImages}" var="si" varStatus="status">
				<img
					<c:choose>
					 <c:when test="${status.index == 0}">
					 	style="display: block;"
					 </c:when>
					 <c:otherwise>
					 	style="display: none;"
					 </c:otherwise>
					</c:choose>
					 id="pic${status.count}" src="/tmall/image/product/${p.id}/${si.id}.jpg" />
			</c:forEach>
		</div>
		<ul id="mpic">
			<c:forEach items="${p.productSingleImages}" var="si" varStatus="status">
			<li>
				<a rel="pic${status.count}"><img class="mpic" src="/tmall/image/product/${p.id}/${si.id}.jpg" /></a>
			</li>
			</c:forEach>
		</ul>
	</div>
	<div class="m-essential">
		<h4 style="margin: 0;">${p.name}</h4>
		<span class="tips">${p.subTitle}</span>
		<div class="juhuasuan">
			<span style="font-size: 18px; color: #FFFFFF;">聚划算</span> 此商品即将参加聚划算，
			<span style="color: #EEA236;">1天19小时</span>后开始，
		</div>
		<div class="price">
			<img src="/tmall/image/site/gouwuquan.png" /><span> 全天猫实物商品通用</span>
			<div>
				专柜价<span style="margin-left: 20px; text-decoration: line-through;">¥<fmt:formatNumber value="${p.orignalPrice}" pattern="#,#00.00#" /></span>
			</div>
			<div>
				促销价<span style="margin-left: 20px;font-size: 14px; color: #FF0036;">¥<span style="font-size: 20px; font-weight: 700;"><fmt:formatNumber value="${p.promotePrice}" pattern="#,#00.00#" /></span></span>
			</div>
		</div>
		<div class="m-salesAndReview">
			<div>销量 <span style="color:#ff0036;">${p.saleCount}</span></div>
			<span style="float: left;">|</span>
			<div>累计评价 <span style="color:#ff0036;">${p.reviewCount}</span></div>
		</div>
		<div class="m-selected-cnt">
			<span>数量 </span>
			<input id="selectedCount" type="text" value="1" />
			<span class="change-button">
							<a id="countUp" href="#up" class="glyphicon glyphicon-chevron-up"></a>
							<a id="countDown" href="#down" class="glyphicon glyphicon-chevron-down"></a>
						</span>
			<span> 件 库存${p.stock}件</span>
		</div>
		<div style="margin: 20px 0; font-size: 12px; color: #666666;">
			服务承诺 正品保证 极速退款 赠运费险 七天无理由退换
		</div>
		<div>
			<a href=""><button class="buy">立即购买</button></a>
			<a href=""><button class="shopcart"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button></a>
		</div>
	</div>
</div>
<div class="g-description">
	<div class="m-tabs">
		<a href="#11" id="img_a" class="active">商品详情</a>
		<a href="#12" id="review_a">累计评价 ${p.reviewCount}</a>
	</div>
	<div id="img" class="m-description-img">
		<div class="details">
			<div class="title">产品参数：</div>
			<table border="0">
				<c:forEach items="${theppvs}" var="ppv" varStatus="status">
				<c:if test="${status.index % 3 == 0}"><tr></c:if>
					<td>${ppv.property.name}: ${ppv.value}</td>
				<c:if test="${status.index % 3 == 2}"></tr></c:if>
				</c:forEach>
			</table>
			<c:forEach items="${p.productDetailsImages}" var="di">
				<img src="/tmall/image/product/${p.id}/${di.id}.jpg" width="790px" />
			</c:forEach>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#mpic li a").mouseenter(function() {
			$("#fullpic img").css("display", "none");
			$("#" + $(this).attr("rel")).css("display", "block");
		})
		$("#selectedCount").keyup(function() {
			var re = /^[0-9]*[0-9]$/;
			var s = $(this).val();
			if(s === "")
				$(this).val("1");
			else if(!re.test(s))
				$(this).val(s.substr(0, s.length - 1));
		})
		$("#selectedCount").blur(function() {
			var n = parseInt($(this).val());
			if(n > ${p.stock})
				$(this).val("${p.stock}");
		})
		$("#countUp").click(function() {
			var n = parseInt($("#selectedCount").val());
			if(++n <= 66)
				$("#selectedCount").val(n.toString());
		})
		$("#countDown").click(function() {
			var n = parseInt($("#selectedCount").val());
			if(--n >= 1)
				$("#selectedCount").val(n.toString());
		})
		$("#img_a").click(function() {
			$("#review_a").removeClass("active");
			$("#img_a").addClass("active");
			$("#review").css("display", "none");
			$("#img").css("display", "block");
		})
		$("#review_a").click(function() {
			$("#img_a").removeClass("active");
			$("#review_a").addClass("active");
			$("#img").css("display", "none");
			$("#review").css("display", "block");
		})
	})
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>