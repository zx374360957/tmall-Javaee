<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<div class="navigator">
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<a href="admin"><img src="image/site/tmallbuy.png"
			class="navbar-left" alt="tmall" height="45px" /></a> <a
			class="navbar-brand" href="admin">天猫后台</a> <a class="navbar-brand"
			href="admin_Category_list">分类管理</a> <a class="navbar-brand"
			href="admin_User_list">用户管理</a> <a class="navbar-brand"
			href="admin_Order_list">订单管理</a>
		<c:if test="${!empty sessionScope.admin}">
		<a href="#" class="navbar-brand navbar-right">退出登录</a>
			<a href="#" class="navbar-brand navbar-right">
				${sessionScope.admin.adminName} <span class="label label-info">${sessionScope.admin.role}
			</a>
		</c:if>
	</nav>

</div>