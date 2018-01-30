<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--注册</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-register">
			<table border="0" style="margin: 0 auto;">
				<tr>
					<td><strong>设置会员名</strong></td>
				</tr>
				<tr>
					<td>登陆名</td>
					<td><input type="text" id="userName" name="userName" placeholder="会员名一旦设置成功，无法修改" /></td>
					<td><span id="nameTips" style="display: none;color: #FF0036;">当前会员名以已存在</span></td>
				</tr>
				<tr>
					<td><strong>设置登陆密码</strong></td>
					<td style="text-align: left;">登陆时验证，保护账号信息</td>
				</tr>
				<tr>
					<td>登陆密码</td>
					<td><input type="password" id="password" placeholder="设置你的登陆密码" /></td>
				</tr>
				<tr>
					<td>密码确认</td>
					<td>
						<input type="password" id="confirm" placeholder="请再次输入你的密码" />
					</td>
					<td>
						<span id="pwdTips" style="display: none;color: #FF0036;">两次输入密码不一致</span>
					</td>
				</tr>
				<tr>
					
				</tr>
			</table>
			<button type="button" id="register" class="sign-up-btn">
				提交
			</button>
		</div>

<script type="text/javascript">
	$("#register").click(function(){
		if ($("#userName").val().length === 0){
			alert("名字不能为空");
			$("#userName").focus();
			return;
		}
		if ($("#password").val().length === 0){
			alert("密码不能为空");
			$("#password").focus();
			return;
		}
		if ($("#confirm").val().length === 0){
			alert("密码不能为空");
			$("#confirm").focus();
			return;
		}
		if ($("#password").val() !== $("#confirm").val()){
			$("#pwdTips").show();
			return;
		}
		var url = "AjaxServlet";
		$.post(url,{
				method: "register",
				user: $("#userName").val(),
				password: $("#password").val()
			},function(result){
				if (result === 'fail'){
					$("#nameTips").show();
				} else{
					window.location.assign('http://localhost:8080/tmall/foreindex');
				}
			}
		);
	})
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>