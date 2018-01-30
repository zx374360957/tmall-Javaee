<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    
<div class="g-search-mid">
	<a class="u-search-logo" href="foreindex"><img src="/tmall/image/site/logo.gif" alt="天猫"></a>
	<div class="m-search">
		<form action="foresearch" method="post">
			<input class="u-search-input" type="text" id="name" name="name" placeholder="风冷无霜 大容量更优惠" />
			<button class="u-search-button">搜索</button>
		</form>
		<nav class="u-search-hot">
			<c:forEach items="${thecs}" var="c" varStatus="status">
				<c:if test="${status.index >= 5 && status.index <= 8}">
					<a class="link" href="forecategory?cid=${c.id}">${c.name}</a>
				</c:if>
			</c:forEach>
		</nav>
	</div>
</div>