<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>

<title>分类管理</title>

<div class="g-mid">
	<br />
	<div class="label label-info" style="font-size: 20px;">分类管理</div>
	<br />
	<br />
	<table border="1" class="m-list">
		<thead>
			<tr>
				<th width="10%">ID</th>
				<th width="40%">图片</th>
				<th width="10%">分类名称</th>
				<th width="10%">属性管理</th>
				<th width="10%">产品管理</th> 
				<th width="10%">编辑</th>
				<th width="10%">删除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${thecs}" var="c">
				<tr>
					<td>${c.id}</td>
					<td><img height="40px" src="image/category/${c.id}.jpg" /></td>
					<td>${c.name}</td>
					<td><a href="admin_Property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
					<td><a href="admin_Product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td> 
					<td>
						<a href="admin_Category_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a>
					</td>
					<td>
						<a class="delete" href="admin_Category_delete?id=${c.id}"><span class="glyphicon glyphicon-trash"></span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="g-mid">
	<%@ include file="../include/admin/adminPage.jsp" %>
</div>

<div class="panel panel-warning m-category-add">
	<div class="panel-heading">新增分类</div>
	<div class="panel-body">
		<form id="submit" method="post" action="admin_Category_add" enctype="multipart/form-data">
			<table border="0" class="addtable">
				<tr>
					<td>分类名称</td>
					<td><input id="name" name="name" type="text" /></td>
				</tr>
				<tr>
					<td>分类图片</td>
					<td><input id="categorypic" type="file" accept="image/*" name="filepath" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-success">提交</button>
					</td>
				</tr>
			</table>

		</form>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">删除分类</h4>
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
	$(".delete").click(function(){
		var link = $(this).prop("href");
		$("#deleteConfirm").prop("href", link);
		$("#myModal").modal("show");
		return false;
	})
	$("#submit").submit(function(){
		if (checkEmpty("name", "分类名称"))
			return false;
		if (checkEmpty("categorypic", "分类图片"))
			return false;
		return true;
	})
</script>

<%@ include file="../include/admin/adminFooter.jsp" %>