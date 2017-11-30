<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>

<title>产品管理</title>

<div >
	<ul class="m-admin-title">
		<li><a href="admin_Category_list">所有分类 </a></li>
		<li>/</li>
		<li><a href="admin_Category_list"> ${c.name}</a></li>
		<li>/</li>
		<li>产品管理</li>
	</ul>
</div>

<div class="g-mid">
	<table border="1" class="m-list">
		<thead>
			<tr>
				<th>ID</th>
				<th>图片</th>
				<th width="500px">产品名称</th>
				<th width="500px">产品小标题</th>
				<th>原价格</th>
				<th>优惠价格</th>
				<th>库存数量</th>
				<th>图片管理</th>
				<th>设置属性</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${theps}" var="p">
			<tr>
				<td>${p.id}</td>
				<td><img src="/tmall/image/product/${p.id}.jpg" alt="" /></td>
				<td>${p.name}</td>
				<td>${p.subTitle}</td>
				<td>${p.orignalPrice}</td>
				<td>${p.promotePrice}</td>
				<td>${p.stock}</td>
				<td><a href="admin_Product_edit?cid=${param.cid}&id=${p.id}"><span class="glyphicon glyphicon-picture"></span></a></td>
				<td><a href="#1"><span class="glyphicon glyphicon-list"></span></a></td>
				<td><a href="admin_Product_edit?cid=${param.cid}&id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
				<td><a class="delete" href="admin_Product_delete?cid=${param.cid}&id=${p.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="g-mid">
	<%@ include file="../include/admin/adminPage.jsp" %>
</div>

<div class="panel panel-warning m-property-add">
	<div class="panel-heading">新增属性</div>
	<div class="panel-body">
		<form id="submit" method="post" action="admin_Product_add" enctype="multipart/form-data">
			<table border="0" class="addtable">
				<tr>
					<td>产品名称</td>
					<td><input id="name" name="name" type="text" /></td>
				</tr>
				<tr>
					<td>产品小标题</td>
					<td><input id="subtitle" name="subtitle" type="text" /></td>
				</tr>
				<tr>
					<td>原价格</td>
					<td><input id="orignalPrice" name="orignalPrice" type="text" /></td>
				</tr>
				<tr>
					<td>优惠价格</td>
					<td><input id="promotePrice" name="promotePrice" type="text" /></td>
				</tr>
				<tr>
					<td>库存</td>
					<td><input id="stock" name="stock" type="text" /></td>
				</tr>
				<tr>
					<td>
						<input id="cid" name="cid" type="hidden" value="${param.cid}"/>
					</td>
					<td>
						<button id="submitButton" type="submit" class="btn btn-success">提交</button>
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
	$(".delete").click(function(){
		var link = $(this).prop("href");
		$("#deleteConfirm").prop("href", link);
		$("#myModal").modal("show");
		return false;
	})
	$("#submit").submit(function(){
		if (checkEmpty("name", "产品名称"))
			return false;
		if (checkEmpty("subtitle", "产品小标题"))
			return false;
		if (checkEmpty("orignalPrice", "原价格"))
			return false;
		if (!checkNumber("orignalPrice", "原价格"))
			return false;
		if (checkEmpty("promotePrice", "优惠价格"))
			return false;
		if (!checkNumber("promotePrice", "优惠价格"))
			return false;
		if (checkEmpty("stock", "库存"))
			return false;
		if (!checkNumber("stock", "库存"))
			return false;
		return true;
	})
</script>
<%@ include file="../include/admin/adminFooter.jsp" %>