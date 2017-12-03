<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>

<title>订单项管理</title>

<div>
	<ul class="m-admin-title">
		<li><a href="admin_Order_list"> 订单${o.id}</a></li>
		<li>/</li>
		<li>订单项管理</li>
	</ul>
</div>

<div class="g-mid m-order">
	<table border="1" class="m-list">
		<thead>
			<tr>
				<th>ID</th>
				<th colspan="2">商品名称</th>
				<th>商品数量</th>
				<th>原价格</th>
				<th>促销价格</th>
				<th>商品总价</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${theois}" var="oi">
			<tr>
				<td>${oi.id}</td>
				<td>${oi.product.firstProductImage}</td>
				<td>${oi.product.name}</td>
				<td>${oi.number}</td>
				<td>${oi.product.orignalPrice}</td>
				<td>${oi.product.promotePrice}</td>
				<td>${oi.product.promotePrice * oi.number}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="g-mid">
	<%@ include file="../include/admin/adminPage.jsp" %>
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
</script>
<%@ include file="../include/admin/adminFooter.jsp" %>