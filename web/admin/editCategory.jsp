<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 

<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>

<title>分类编辑</title>


<div class="panel panel-warning m-category-edit">
	<div class="panel-heading">分类编辑</div>
	<div class="panel-body">
		<form id="submit" method="post" action="admin_Category_update" enctype="multipart/form-data">
			<table border="0" class="addtable">
				<tr>
					<td>分类ID</td>
					<td>${param.id}</td>
				</tr>
				<tr>
					<td>分类新名称</td>
					<td><input id="name" name="name" type="text"/></td>
				</tr>
				<tr>
					<td>分类新图片</td>
					<td><input id="categorypic" type="file" accept="image/*" name="filepath"/></td>
				</tr>
				<tr>
					<td><input id="id" name="id" type="hidden" value="${param.id}"/></td>
					<td>
					<button type="submit" class="btn btn-success">提交</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script>
	$("#submit").submit(function(){
		if (checkEmpty("name", "分类名称"))
			return false;
		if (checkEmpty("categorypic", "分类图片"))
			return false;
		return true;
	})
</script>

<%@ include file="../include/admin/adminFooter.jsp" %>