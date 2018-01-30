<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--我的订单</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid">
	<ul class="m-order-title">
		<li class="selected">
			<a href="#all">所有订单</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="#unpay">待付款</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="#unship">待发货</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="#unreceive">待收货</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="#unreview">待评价</a>
		</li>
	</ul>
	<div class="m-order-head">
		<span style="width: 60%;">宝贝</span>
		<span style="width: 10%;">单价</span>
		<span style="width: 8%;">数量</span>
		<span style="width: 12%;">实付款</span>
		<span style="width: 10%;">交易操作</span>
	</div>
	<c:forEach items="${theos}" var="o">
		<c:choose>
			<c:when test="${o.status == '待发货'}">
				<div class="m-order order-unship">
			</c:when>
			<c:when test="${o.status == '待付款'}">
				<div class="m-order order-unpay">
			</c:when>
			<c:when test="${o.status == '待收货'}">
				<div class="m-order order-unrecive">
			</c:when>
			<c:otherwise>
				<div class="m-order order-unreview">
			</c:otherwise>
		</c:choose>
		<div class="u-orderId">
			<div style="width: 60%;"><strong><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${o.createDate}" /></strong> 订单号: ${o.orderCode}</div>
			<div><img src="/tmall/image/site/tmallbuy.png" />天猫商场</div>
			<a href="" rel="${o.id}">
				<div class="delete glyphicon glyphicon-trash"></div>
			</a>
		</div>
		<div class="u-orderName">
			<table border="1" cellpadding="10px">
				<c:forEach items="${o.orderItems}" var="oi" varStatus="status">
					<tr>
						<td style="width: 10%;"><img class="item-img" src="/tmall/image/product/${oi.product.id}/${oi.product.firstProductImage.id}.jpg" /></td>
						<td class="name" style="width: 50%;">
							<a href="foreproduct?pid=${oi.product.id}">${oi.product.name}</a>
							<div>
								<img src="/tmall/image/site/creditcard.png" />
								<img src="/tmall/image/site/7day.png" />
								<img src="/tmall/image/site/promise.png" />
							</div>
						</td>
						<td style="width: 10%;">
							<span style="color: #8C8C8C; text-decoration: line-through;">
							￥<fmt:formatNumber value="${oi.product.orignalPrice}" pattern="#,#00.00#" />
						</span> <br />
							￥<fmt:formatNumber value="${oi.product.promotePrice}" pattern="#,#00.00#" />
						</td>
						<td style="width: 8%;border-left: 1px solid #E6E6E6;">${oi.number}</td>
						<td style="width: 12%;border-left: 1px solid #E6E6E6;">
							<strong>￥<fmt:formatNumber value="${oi.product.promotePrice * oi.number}" pattern="#,#00.00#" /></strong><br />
							<span style="font-size: 12px;">(含运费:￥0.00)</span>
						</td>
						<td style="width: 10%;border-left: 1px solid #E6E6E6;">
							<c:if test="${status.index == 0}">
								<c:choose>
							<c:when test="${o.status == '待发货'}">
								<span>待发货</span>
							</c:when>
							<c:when test="${o.status == '待付款'}">
								<button class="btn btn-info payment"><a href="foretopay?oid=${o.id}" style="color: white;">付款</a></button>
							</c:when>
							<c:when test="${o.status == '待收货'}">
								<button class="btn btn-info payment"><a href="foretoConfirm?oid=${o.id}" style="color: white;">确认收货</a></button>
							</c:when>
							<c:when test="${o.status == '待评价'}">
								<button class="btn btn-default review-btn"><a href="foretoReview?oid=${o.id}" style="color: black;">评价</a></button>
							</c:when>
							<c:otherwise>
								<span>已完成</span>
							</c:otherwise>
							</c:choose>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>
	</c:forEach>
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
        	<a id="deleteConfirm" href="" style="color: white;">继续删除</a>
        </button>
        <input id="oid" type="hidden" value="" />
      </div>
    </div>
  </div>
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".m-order-title li").click(function() {
				$(".m-order-title li").removeClass("selected");
				$(this).addClass("selected");
				var s = $(this).find("a").prop("href");
				s = s.substring(s.indexOf("#"));
				switch(s) {
					case "#unpay":
						$(".m-order").hide();
						$(".order-unpay").show();
						break;
					case "#unship":
						$(".m-order").hide();
						$(".order-unship").show();
						break;
					case "#unreceive":
						$(".m-order").hide();
						$(".order-unrecive").show();
						break;
					case "#unreview":
						$(".m-order").hide();
						$(".order-unreview").show();
						break;
					default:
						$(".m-order").show();
				}
			})
			$("#deleteConfirm").click(function(){
				var value = $("#oid").val();
				$.post("AjaxServlet",{
						method: "deleteOrder",
						oid: value
					}, function(){
						window.location.reload();
					}
				)
				return false;
			})
			$(".delete").click(function(){
				var oidValue = $(this).parent().prop("rel");
				$("#oid").val(oidValue);
				$("#myModal").modal("show");
				return false;
			})
		})
	</script>

	<%@ include file="../include/fore/separator.jsp" %>
	<%@ include file="../include/fore/footer.jsp" %>