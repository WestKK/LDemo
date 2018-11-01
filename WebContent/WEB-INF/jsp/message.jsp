<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Dashboard Template for Bootstrap</title>

<link rel="stylesheet" href="css/layui.css">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">消息系统后台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">仪表盘</a></li>
					<li><a href="#">设置</a></li>
					<li><a href="#">${sessionScope.users.userName}</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
				<input type="text" id="qTitle" name="qTitle" class="form-control"
					placeholder="搜索..." value="${requestScope.tt}">
				<button type="submit" class="btn btn-danger">查询</button>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="users.jsp">消息列表<span class="sr-only">(current)</span></a>
					</li>
					<li><a href="addUsers.jsp">全部消息</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="question.jsp">已读消息</a></li>
					<li class="active"><a href="question_jstl.jsp">问题列表_jstl</a></li>
					<li><a href="answer_jstl.jsp">答案列表_jstl</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="sub-header">问题列表</h1>
				<div class="table-responsive">
					<table class="table table-striped">

						<thead>
							<tr>
								<th>编号</th>
								<th>标题</th>
								<th>内容</th>
								<th>日期</th>
								<th>发布人</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${requestScope.pd == null }">
								<jsp:forward page="qs.do?qa=queryquestion_jstl"></jsp:forward>
							</c:if>

							<c:forEach items="${requestScope.pd.data}" var="q">
								<tr>
									<td>${q.qId}</td>
									<td>${q.title}</td>
									<td>${q.content}</td>
									<td>${q.qDate}</td>
									<td>${q.userName}</td>
									<td><button onclick="btn_edit(1)">
											<i class="icon-edit bigger-120"></i>编辑
										</button></td>
									<td><button onclick="btn_delete(1)">
											<i class="icon-trash bigger-120"></i>删除
										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="test1" style="text-align: center"></div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/layui.all.js"></script>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		layui
				.use(
						'laypage',
						function() {
							var laypage = layui.laypage;

							var c = $
							{
								pd.total
							}
							;

							var l = $
							{
								pd.pageSize
							}
							;

							var p = $
							{
								pd.page
							}
							;

							//执行一个laypage实例
							laypage
									.render({
										elem : 'test1' //注意，这里的 test1 是 ID，不用加 # 号
										,
										count : c, //数据总数，从服务端得到
										limit : l,
										skip : '#5e7cdf',
										layout : [ 'prev', 'page', 'next',
												'limit', 'skip', 'refresh' ],
										curr : function() {
											var page = p; // 当前页(后台获取到的)
											return page ? page : 1; // 返回当前页码值
										}(),
										jump : function(obj, first) {
											if (!first) {
												location.href = "qs.do?qa=queryquestion_jstl&page="
														+ obj.curr
														+ "&pageSize="
														+ obj.limit
														+ "&qTitle="
														+ $("#qTitle").val();
											}
										}
									});
						});
	</script>

</body>

</html>