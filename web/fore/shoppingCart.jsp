<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--购物车</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div style="padding: 20px;"></div>
<div class="g-mid">
	<div class="m-settle">
		<button class="m-settle-btn" disabled="disabled">结算</button>
		<div class="m-settle-text">
			已选商品 (不含运费)
			<span class="totalMoney" style="color: #FF0036;">
										￥0.00
									</span>
		</div>
	</div>
	<table class="m-shopping-item">
		<thead>
			<tr>
				<th width="110px">
					<span>
						<img class="selectAll" src="/tmall/image/site/cartNotSelected.png" alt="" />
						<img class="unselectAll" src="/tmall/image/site/cartSelected.png" alt=""/>
						全选
					</span>
				</th>
				<th width="415px">商品信息</th>
				<th width="100px">单价</th>
				<th width="120px">数量</th>
				<th width="120px">金额</th>
				<th width="80px">操作</th>
			</tr>
		</thead>
		<tbody>
			<form action="foretoCreateOrder" id="cartForm" method="get">
			<c:forEach items="${sessionScope.shoppingCart}" var="cart">
				<tr>
					<td>
						<span class="g-check">
							<img class="select" src="/tmall/image/site/cartNotSelected.png"/>
							<img class="unselect" src="/tmall/image/site/cartSelected.png"/>
						</span>
						<input class="pidSelect" type="checkbox" name="cartPid" checked="false" value="${cart.product.id}" />
						<img class="item-img" src="/tmall/image/product/${cart.product.id}/${cart.product.firstProductImage.id}.jpg" />
					</td>
					<td class="item-name">
						<a href="foreproduct?pid=${cart.product.id}">${cart.product.name}</a>
						<div>
							<img src="/tmall/image/site/creditcard.png" />
							<img src="/tmall/image/site/7day.png" />
							<img src="/tmall/image/site/promise.png" />
						</div>
					</td>
					<td>
						<div style="color: #8C8C8C; text-decoration: line-through;">￥
							<fmt:formatNumber value="${cart.product.orignalPrice}" pattern="#,#00.00#" />
						</div>
						<div class="s-redColor unit-price">￥
							<fmt:formatNumber value="${cart.product.promotePrice}" pattern="#,#00.00#" />
						</div>
					</td>
					<td class="change">
						<button class="down" type="button">-</button>
						<input type="text" class="countValue" value="1" />
						<button class="up" type="button">+</button>
					</td>
					<td>
						<div class="s-redColor price">￥
							<fmt:formatNumber value="${cart.product.promotePrice}" pattern="#,#00.00#" />
						</div>
					</td>
					<td>
						<a class="delete" href="#1">删除</a>
					</td>
				</tr>
			</c:forEach>
			</form>
		</tbody>
	</table>
	
	<div class="m-settle-big">
		<span style="position: relative; left: 40px; top: 5px;">
					<img class="selectAll" src="/tmall/image/site/cartNotSelected.png" alt="" />
					<img class="unselectAll" src="/tmall/image/site/cartSelected.png" alt=""/>
					全选
				</span>
		<button id="settle" class="m-settle-btn" disabled="disabled">结算</button>
		<div class="m-settle-text">
			已选商品
			<span id="count" style="color: #FF0036; font-size: 16px;">
				0
			</span> 件 合计(不含运费)：
			<span class="totalMoney" style="color: #FF0036; font-size: 18px;">
				￥0.00
			</span>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
       <div class="m-login-input">
				<strong style="font-size: 18px;">账户登录</strong>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<div class="input">
					<span class="glyphicon glyphicon-user"></span>
					<input type="text" id="usr" placeholder="手机/会员名/邮箱" />
				</div>
				<div class="input">
					<span class="glyphicon glyphicon-lock"></span>
					<input type="password" id="pwd" placeholder="密码" />
				</div>
				<div style="height: 30px;">
					<div class="forgetPWD">
						<a href="#1">忘记登录密码</a>
					</div>
					<div class="freeRegist">
						<a href="#1">免费注册</a>
					</div>
					
				</div>
				<div id="loginTips" class="loginTips">
					用户名或者密码错误
				</div>
				<button id="login"class="login-btn">登录</button>
			</div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".selectAll").click(function() {
			$(".selectAll").css("display", "none");
			$(".unselectAll").css("display", "inline-block");
			$(".select").css("display", "none");
			$(".unselect").css("display", "inline-block");
			$(".pidSelect").prop("checked", true);
			if(countSelected() > 0) {
				$(".m-settle-btn").addClass("s-abled").removeAttr("disabled");
			} else {
				$(".m-settle-btn").removeClass("s-abled").attr("disabled", true);
			}
		})
		$(".unselectAll").click(function() {
			$(".selectAll").css("display", "inline-block");;
			$(".unselectAll").css("display", "none");
			$(".select").css("display", "inline-block");
			$(".unselect").css("display", "none");
			$(".pidSelect").prop("checked", false);
			if(countSelected() > 0) {
				$(".m-settle-btn").addClass("s-abled").removeAttr("disabled");
			} else {
				$(".m-settle-btn").removeClass("s-abled").attr("disabled", true);
			}
		})
		$(".g-check").click(function() {
			$(this).children().toggle();
			
			var pidDom = $(this).parent().find(".pidSelect");
			var s = (pidDom.prop("checked") === true) ? false : true;
			pidDom.prop("checked", s);
			
			var lenOfAll = $(".g-check").length;
			var len = countSelected();
			if(len == lenOfAll) {
				$(".selectAll").css("display", "none");
				$(".unselectAll").css("display", "inline-block");
			} else {
				$(".selectAll").css("display", "inline-block");;
				$(".unselectAll").css("display", "none");
			}
			if(len > 0) {
				$(".m-settle-btn").addClass("s-abled").removeAttr("disabled");
			} else {
				$(".m-settle-btn").removeClass("s-abled").attr("disabled", true);
			}
		})
		$(".down").click(function() {
			var v = $(this).parent().find(".countValue");
			var vnum = parseInt(v.val()) - 1;
			if(vnum == 0)
				return;
			v.val(vnum + "");
			countSelected()
		})
		$(".up").click(function() {
			var v = $(this).parent().find(".countValue");
			v.val((parseInt(v.val()) + 1) + "");
			countSelected();
		})
		$(".countValue").keyup(function() {
			var re = /^[0-9]*[0-9]$/;
			var s = $(this).val();
			if(s === "")
				$(this).val("1");
			else if(!re.test(s))
				$(this).val(s.substr(0, s.length - 1));
			countSelected();
		})
		$(".delete").click(function() {
			$.post("AjaxServlet", {
				method: "deleteShoppingCart",
				pid: $(this).parent().find(".pid").val(),
			}, function() {
				window.location.reload(true);
			})
		})
		$(".m-settle-btn").click(function(){
			
			<c:choose>
			<c:when test="${empty sessionScope.user}">
				$("#myModal").modal("show");
			</c:when>
			<c:otherwise>
			$("#cartForm").submit();
			</c:otherwise>
			</c:choose>
		})
		$("#login").click(function(){
		var url = "AjaxServlet";
		$.post(url,{
				method: "login",
				user: $("#usr").val(),
				password: $("#pwd").val(),
			},function(result){
				if (result === 'fail'){
					$("#loginTips").show();
				} else{
					$("#cartForm").submit();
				}
			}
		);
		return false;
	})
	})
</script>
<script type="text/javascript">
	
	function countSelected() {
		var cnt = 0;
		var m = 0;
		var re = /[0-9\.]/g;

		$(".g-check").each(function() {
			var trDom = $(this).parent().parent();
			var unitPriceString = trDom.find(".unit-price").text();
			var matches = unitPriceString.match(re);
			if(typeof matches !== "object")
				return cnt;
			var unitPrice = parseFloat(matches.join(""));
			var count = parseInt(trDom.find(".countValue").val());
			var price = unitPrice * count;
			trDom.find(".price").text(price.toFixed(2).toString());
			var color = "#FFFFFF";

			if($(this).find(".unselect").css("display") !== "none") {
				++cnt;

				color = "#fff8e1";
				m += price;
			}
			trDom.css("background-color", color);
		})

		$(".totalMoney").text("￥" + m.toFixed(2).toString());
		$("#count").text(cnt + "");

		return cnt;
	}
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>