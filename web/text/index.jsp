<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>test</title>
	<script src="../js/jquery/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
	<script src="../js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
<div class="panel panel-warning m-property-add">
	<div class="panel-heading">新增属性</div>
	<div class="panel-body">
		<form id="submit" method="post" action="/tmall/TestServlet" enctype="multipart/form-data">
			<table border="0" class="addtable">
				<tr>
					<td>属性名称</td>
					<td><input id="name" name="name" type="text" /></td>
				</tr>
				<tr>
					<td>
						<input id="cid" name="cid" type="hidden" value="3"/>
					</td>
					<td>
						<button type="submit" class="btn btn-success">提交</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>