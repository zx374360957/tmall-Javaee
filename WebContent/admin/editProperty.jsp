<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<title>属性编辑</title>

<div class="panel panel-warning m-property-edit">
	<div class="panel-heading">编辑属性</div>
	<div class="panel-body">
		<form id="submit" method="post" action="admin_Property_add" enctype="multipart/form-data">
			<table border="0" class="addtable">
				<tr>
					<td>属性id</td>
					<td>${param.id}</td>
					<td>
						<input id="id" name="id" type="hidden" value="${param.id}" />
					</td>
				</tr>
				<tr>
					<td>属性名称</td>
					<td><input id="name" name="name" type="text" /></td>
				</tr>
				<tr>
					<td>
						<input id="cid" name="cid" type="hidden" value="${param.cid}" />
					</td>
					<td>
						<button id="submitButton" type="submit" class="btn btn-success">提交</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script type="text/javascript">
	$("#submit").submit(function(){
		if (checkEmpty("name", "属性名称"))
			return false;
		return true;
	})
</script>

<%@ include file="../include/admin/adminFooter.jsp" %>