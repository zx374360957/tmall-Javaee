<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>

<title>属性管理</title>

<div >
	<ul class="m-property-title">
		<li><a href="admin_Category_list">所有分类 </a></li>
		<li>/</li>
		<li><a href=""> ${c.name}</a></li>
		<li>/</li>
		<li> 属性管理</li>
	</ul>
</div>

<div class="g-mid">
	<table border="1" class="m-list">
		<thead>
			<tr>
				<th width="20%">ID</th>
				<th width="40%">属性名称</th>
				<th width="20%">编辑</th>
				<th width="20%">删除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${thepps}" var="pp">
			<tr>
				<td>${pp.id}</td>
				<td>${pp.name}</td>
				<td><a href="admin_Property_edit?cid=${param.cid}&id=${pp.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
				<td><a class="delete" href="admin_Property_delete?cid=${param.cid}&id=${pp.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
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
		<form id="submit" method="post" action="admin_Property_add" enctype="multipart/form-data">
			<table border="0" class="addtable">
				<tr>
					<td>属性名称</td>
					<td><input id="name" name="name" type="text" /></td>
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
		if (checkEmpty("name", "属性名称"))
			return false;
		return true;
	})
</script>
<%@ include file="../include/admin/adminFooter.jsp" %>