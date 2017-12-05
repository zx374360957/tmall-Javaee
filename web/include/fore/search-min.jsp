<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    
<div class="g-search-mid">
	<a class="u-search-mlogo" href="/tmall/"><img src="/tmall/image/site/simpleLogo.png" alt="tmall天猫" /></a>
	<div>
		<div class="m-search-min">
			<form action="" name="" method="">
				<input class="u-search-input-min" type="text" placeholder="风冷无霜 大容量更优惠" />
				<button type="submit" class="u-search-button-min">搜索</button>
			</form>
			<nav class="u-search-hot">
				<c:forEach items="${thecs}" var="c" varStatus="status">
				<c:if test="${status.index >= 5 && status.index <= 8}">
					<a class="link" href="#5">${c.name}</a>
				</c:if>
				</c:forEach>
			</nav>
		</div>
	</div>
</div>