<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>
<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<title>天猫tmall.com--${c.name}</title>

<div class="g-mid">
	<div class="m-sort">
		<a href="forecategory?cid=${c.id}">
			<img src="image/category/${c.id}.jpg" alt="title" />
		</a>
		<br />
		<ul class="methods">
			<li>
				<a href="#1" class="active">综合<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">人气<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">销量<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">价格<span class="glyphicon glyphicon-sort"></span></a>
			</li>
		</ul>
		<div class="input">
			<input type="text" placeholder="请输入" />
			<span>-</span>
			<input type="text" placeholder="请输入" />
		</div>
	</div>
	<br />
	<div class="m-category">
		<table border="0">
			<c:forEach items="${theps}" var="p" varStatus="status">
				<c:if test="${status.index % 5 == 0}">
					<tr>
				</c:if>
				<td>
					<div class="items">
						<a href="/tmall/foreproduct?pid=${p.id}"><img src="image/product/${p.id}/${p.firstProductImage.id}.jpg" alt="商品" width="177px" height="180px" /></a>
						<br />
						<div>

							<a class="name" href="/tmall/foreproduct?pid=${p.id}">${fn:substring(p.name, 0, 30)}</a>
							<div class="s-redColor price">
								¥
								<fmt:formatNumber value="${p.promotePrice}" pattern="#,#00.00#" />
							</div>
						</div>
					</div>
				</td>
				<c:if test="${status.index % 5 == 4}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>

</div>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>