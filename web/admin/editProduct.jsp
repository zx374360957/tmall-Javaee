<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ include file="../include/admin/adminheader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<title>属性编辑</title>

<div class="panel panel-warning m-property-edit">
	<div class="panel-heading">产品编辑</div>
	<div class="panel-body">
		<form id="submit" method="post" action="admin_Product_update" enctype="multipart/form-data">
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
						<input id="id" name="id" type="hidden" value="${param.id}"/>
					</td>
					<td>
						<input id="cid" name="cid" type="hidden" value="${param.cid}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="submitButton" type="submit" class="btn btn-success">提交</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>


<script type="text/javascript">
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