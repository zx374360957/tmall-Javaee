<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--登录</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid">
	<a href="/tmall/" style="margin: 20px;"><img src="image/site/simpleLogo.png" /></a>
	<div class="m-sign-in">
			<div class="m-login-input">
				<strong style="font-size: 18px;">账户登录</strong>
				<div class="input">
					<span class="glyphicon glyphicon-user"></span>
					<input type="text" id="usr" placeholder="手机/会员名/邮箱" />
				</div>
				<div class="input">
					<span class="glyphicon glyphicon-lock"></span>
					<input type="password" id="pwd" placeholder="密码" />
				</div>
				<div style="height: 30px;">
					<div class="forgetPWD">
						<a href="#1">忘记登录密码</a>
					</div>
					<div class="freeRegist">
						<a href="#1">免费注册</a>
					</div>
					
				</div>
				<div id="loginTips" class="loginTips">
					用户名或者密码错误
				</div>
				<button id="login"class="login-btn">登录</button>
			</div>
	</div>
</div>

<script type="text/javascript">
	$("#login").click(function(){
		var url = "AjaxServlet";
		$.post(url,{
				method: "login",
				user: $("#usr").val(),
				password: $("#pwd").val(),
			},function(result){
				if (result === 'fail'){
					$("#loginTips").show();
				} else{
					window.location.assign('http://localhost:8080/tmall/foreindex');
				}
			}
		);
		return false;
	})
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>