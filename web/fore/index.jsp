<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>
<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<title>天猫tmall.com--理想生活上天猫</title>

<div style="position: relative; background-color: #FF0036;">
			<div class="g-mid">
				<div class="m-nav-2">
					<span class="u-nav2-title">商品分类</span>
					<ul class="u-nav2-list">
						<li>
							<a class="link" href="#cloth">服装服饰</a>
						</li>
						<li>
							<a class="link" href="#electric">电器家装</a>
						</li>
						<li>
							<a class="link" href="#medic">医药健康</a>
						</li>
						<li>
							<a class="link" href="#suning">苏宁易购</a>
						</li>
						<li>
							<a class="link" href="#mobile">手机会场</a>
						</li>
						<li>
							<a class="link" href="#car">车品配件</a>
						</li>
						<li>
							<a class="link" href="#global">全球尖货</a>
						</li>
						<li>
							<a class="link" href="#tour">飞猪旅行</a>
						</li>
						
					</ul>
				</div>
			</div>
			<div class="g-slider">
				<div id="carousel" class="carousel slide g-mid">
					<ol class="carousel-indicators">
						<li data-target="#carousel" data-slide-to="0" class="active"></li>
						<li data-target="#carousel" data-slide-to="1"></li>
						<li data-target="#carousel" data-slide-to="2"></li>
						<li data-target="#carousel" data-slide-to="3"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<a herf="#1"><img src="/tmall/image/site/1.jpg" alt="pic1" /></a>
						</div>
						<div class="item">
							<a herf="#2"><img src="/tmall/image/site/2.jpg" alt="pic2" /></a>
						</div>
						<div class="item">
							<a herf="#3"><img src="/tmall/image/site/3.jpg" alt="pic3" /></a>
						</div>
						<div class="item">
							<a herf="#4"><img src="/tmall/image/site/4.jpg" alt="pic4" /></a>
						</div>
					</div>
				</div>
			</div>

		<div class="g-mid">

			<ul class="g-class-list m-class-list">
			<c:forEach items="${thecs}" var="c">
				<li>
					<a href="forecategory?cid=${c.id}">${c.name}</a>
					<div class="m-class-items">
						
						<c:forEach items="${c.productsByRow}" var="ps">
							<div class="m-item-text">
								<c:forEach items="${ps}" var="p">
									<c:if test="${not empty p.subTitle}">
										<c:forEach items="${fn:split(p.subTitle, ' ')}" var="s" varStatus="status">
										<c:if test="${status.index == 0}">
											<a href="foreproduct?pid=${p.id}">${s}</a>
										</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</li>
			</c:forEach>
		</ul>
		</div>
</div>

<div class="g-mid m-types">
		<c:forEach items="${thecs}" var="c">
			<div class="u-type">
				<div class="g-title">
					<div class="left-ico"></div>
					<h4 style="color: #333;" <strong>${c.name}</strong></h4>
					<c:forEach items="${c.products}" var="p">
						<div class="items">
						<a href="foreproduct?pid=${p.id}"><img src="image/product/${p.id}/${p.firstProductImage.id}.jpg" alt="商品" width="180px" height="180px"/></a>
						<br />
						<div>
							<a class="name" href="foreproduct?pid=${p.id}">[热销] ${fn:substring(p.name, 0, 20)}</a>
							<div class="s-redColor price">
								¥<fmt:formatNumber value="${p.promotePrice}" pattern="#,#00.00#" />
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</c:forEach>		
</div>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>