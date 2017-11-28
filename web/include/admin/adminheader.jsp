<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 

<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="js/jquery/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/back/styles.css" />
	<script>
		function checkEmpty(id, name){
			var value = $("#" + id).val();
			if (value.length == 0){
				alert(name + "不能为空");
				$("#"+ id).focus();
				return true;
			}
			return false;
		}
		
		function checkNumber(id, name){
			var value = $("#" + id).val();
			if (value.length == 0){
				alert(name + "不能为空");
				$("#"+ id).focus();
				return false;
			}
			if (isNaN(value)){
				alert(name + "必须为数字");
				$("#"+ id).focus();
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
