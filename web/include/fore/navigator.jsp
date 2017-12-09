<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    
<nav class="m-nav">
	<div class="g-nav-mid">
		<span style="margin-right: 10px;"><span class="glyphicon glyphicon-home s-redColor"></span><a href="foreindex" class="s-redColor">天猫首页</a></span>
		<c:choose>
		<c:when test="${empty sessionScope.user}">
			<span style="margin: 0 15px 0 15px;">喵，欢迎来天猫</span>
			<a class="link" href="forelogin">请登陆</a>
			<a class="link" href="foreregister">免费注册</a>
			<span class="meau">
				<a class="link" href="forelogin">我的订单</a>
				<span class="glyphicon glyphicon-shopping-cart s-redColor"></span>
			<a id="shoppingCart" class="link" href="foreshoppingCart">
				购物车
				${sessionScope.shoppingCartCount}
				件
			</a>
			</span>
		</c:when>
		<c:otherwise>
			<a class="link" href=""><c:out value="${sessionScope.user.name}" escapeXml="true" /></a>
			<a class="link" href="forelogout">退出</a>
			<span class="meau">
				<a class="link" href="forelistOrder">我的订单</a>
				<span class="glyphicon glyphicon-shopping-cart s-redColor"></span>
			<a id="shoppingCart" class="link" href="foreshoppingCart">
				购物车
				${sessionScope.shoppingCartCount}
				件
			</a>
			</span>
		</c:otherwise>
		</c:choose>
	</div>
</nav>