<script>
	$(function(){
		$("ul.m-pagination li.disable a").click(function(){
			return false;
		});
	})
</script>
<nav class="g-mid" style="width: 410px; padding: 30px;">
	<ul class="m-pagination">
		<li <c:if test="${!page.hasPreviouse}"> class="disable" </c:if>>
			<a href="?page.begin=0${page.param}">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
		<li <c:if test="${!page.hasPreviouse}"> class="disable" </c:if>>
			<a href="?page.begin=${page.begin - page.singleCount}${page.param}" aria-label="end">
				<span aria-hidden="true">&lsaquo;</span>
			</a>
		</li>
		<c:forEach begin="0" end="${page.totalPage - 1}" varStatus="status">
			<c:if test="${page.singleCount*status.count-page.begin <= 20 && page.singleCount*status.count-page.begin >= -10}">
				<li <c:if test="${status.index*page.singleCount==page.begin}">class="disable"</c:if>>
				<a href="?page.begin=${status.index*page.singleCount}${page.param}">
					${status.count}
				</a>
				</li>
			</c:if>
		</c:forEach>
		<li <c:if test="${!page.hasNext}"> class="disable" </c:if>>
			<a href="?page.begin=${page.begin + page.singleCount}${page.param}" aria-label="end">
				<span aria-hidden="true">&rsaquo;</span>
			</a>
		</li>
		<li <c:if test="${!page.hasNext}"> class="disable" </c:if>>
			<a href="?page.begin=${page.lastBeg}${page.param}" aria-label="end">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</ul>
</nav>
