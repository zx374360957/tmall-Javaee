<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>

<title>产品管理</title>

<div>
	<ul class="m-admin-title">
		<li>
			<a href="admin_Category_list">所有分类 </a>
		</li>
		<li>/</li>
		<li>
			<a href="admin_Category_list">${p.category.name}</a>
		</li>
		<li>/</li>
		<li>
			<a href="admin_Product_list?cid=${p.category.id}">${p.name}</a>
		</li>
		<li>/</li>
		<li>产品管理</li>
	</ul>
</div>

<div class="g-mid m-product-image">
	<div class="m-product-preview">
		<div class="panel panel-warning">
			<div class="panel-heading">新增产品<span style="color: #ff0036;">预览</span>图片</div>
			<div class="panel-body">
				<form class="submit1" method="post" action="admin_ProductImage_add" enctype="multipart/form-data">
					<table border="0" class="addtable">
					<tr>
						<td>请选择本地图片 尺寸400X400 为佳</td>
						<td><input id="type" name="type" type="hidden" value="single"/></td>
					</tr>
					<tr>
						<td><input id="productpic1" type="file" accept="image/*" name="filepath" /></td>
						<td><input id="pid" name="pid" type="hidden" value="${p.id}"/></td>
					</tr>
					<tr>
						<td class="addButton">
							<button id="submitButton" type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
		<table border="1" class="m-list">
			<thead>
				<tr>
					<th>id</th>
					<th>产品预览图片缩略图</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${p.productSingleImages}" var="si">
					<tr>
						<td>${si.id}</td>
						<td><img width="50px" height="50px" src="/tmall/image/product/${p.id}/${si.id}.jpg" alt="" /></td>
						<td>
							<a class="delete" href="admin_ProductImage_delete?id=${si.id}&pid=${p.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="m-product-details">
		<div class="panel panel-warning">
			<div class="panel-heading">新增产品<span style="color: #ff0036;">详情</span>图片</div>
			<div class="panel-body">
				<form class="submit2" method="post" action="admin_ProductImage_add" enctype="multipart/form-data">
					<table border="0" class="addtable">
					<tr>
						<td>请选择本地图片 宽度800 为佳</td>
						<td><input id="type" name="type" type="hidden" value="details"/></td>
					</tr>
					<tr>
						<td><input id="productpic2" type="file" accept="image/*" name="filepath" /></td>
						<td><input id="pid" name="pid" type="hidden" value="${p.id}"/></td>
					</tr>
					<tr>
						<td class="addButton">
							<button id="submitButton" type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
		<table border="1" class="m-list">
			<thead>
				<tr>
					<th>id</th>
					<th>产品详情图片缩略图</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${p.productDetailsImages}" var="di">
					<tr>
						<td>${di.id}</td>
						<td><img width="50px" height="50px" src="/tmall/image/product/${p.id}/${di.id}.jpg" alt="" /></td>
						<td>
							<a class="delete" href="admin_ProductImage_delete?id=${di.id}&pid=${p.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">删除属性</h4>
			</div>
			<div class="modal-body">
				<p>删除操作不可逆转</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消删除</button>
				<button type="button" class="btn btn-primary">
        	<a id="deleteConfirm" href="#5" style="color: white;">继续删除</a>
        </button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(".delete").click(function() {
		var link = $(this).prop("href");
		$("#deleteConfirm").prop("href", link);
		$("#myModal").modal("show");
		return false;
	})
	$(".submit1").submit(function() {
		if(checkEmpty("productpic1", "产品图片"))
			return false;
		return true;
	})
	$(".submit2").submit(function() {
		if(checkEmpty("productpic2", "产品图片"))
			return false;
		return true;
	})
</script>
<%@ include file="../include/admin/adminFooter.jsp" %>