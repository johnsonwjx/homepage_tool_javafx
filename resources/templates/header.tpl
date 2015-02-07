<div class="header" th:fragment="header">
	<div class="logo">
		<div class="logoTx">
			<a href="index.jsp"><img th:src="${imagepath}+'/'+${header.logopath}"  alt="logo"/></a>
		</div>
		<div style="width:20%;float:left;margin-top: 20px;margin-left: 10px;line-height:18px;font-size:14px;color:#179D49;font-weight:bold;font-family:微软雅黑">
			<div th:each="o:${header.txt1}" th:text="${o.txt}"></div>
		</div>
		<div class="qqtx1">
			<div class="qqtxxb">
				<div th:each="o:${header.txt2}" ><label style="font-size:14px;color:#179D49;font-weight:bold; font-family:'微软雅黑';" th:text="${o.txt}"></label></div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="nav">
		<div>
			<ul>
				<li th:each="o:${header.navs}"><a th:href="${o.url}" th:text="${o.title}" href="index.jsp" class="white">首 页</a></li>
			</ul>
		</div>
	</div>
</div>
