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
			<table border="0" align="center">
				<tr>
					<td><strong>设置会员名</strong></td>
				</tr>
				<tr>
					<td>登陆名</td>
					<td><input type="text" id="name" placeholder="会员名一旦设置成功，无法修改" /></td>
					<td id="nameTips1" style="color: #FF0036; display: block;">当前会员名以已存在</td>
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
					<td><input type="password" id="confirm" placeholder="请再次输入你的密码" /></td>
				</tr>
				<tr><td id="pwdTips" style="color: #FF0036;">两次输入密码不一致</td></tr>
			</table>
			<button id="register" class="sign-up-btn">
				提交
			</button>
		</div>

<script type="text/javascript">
	$("#name").keyup(function(){
		var url = "AjaxServlet";
		var userName = $("#name").val();
		if (userName.length ！== 0){
			$("nameTips1").hidden();
			$.post(url,{
				method: "register",
				name: userName,
				password: $("#password").val(),
				confirm: 
				},function(result){
					if (result === 'success'){
						$("#nameTips").hidden();
						
					} else{
						$("#nameTips").text(result);
						$("#nameTips").show();
					}
				}
			);
		} 
	})
	$("#register").click(function(){
		var url = "AjaxServlet";
		$.post(url,{
				method: "register",
				user: $("#name").val(),
				password: $("#pwd").val(),
			},function(result){
				if (result === 'fail'){
					$("#loginTips").show();
				} else{
					window.location.assign('http://localhost:8080/tmall/foreindex');
				}
			}
		);
	})
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>