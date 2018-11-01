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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layui.css">
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/dashboard.css"
	rel="stylesheet">

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
					<li><a href="#">${sessionScope.userName}</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
				<form class="navbar-form navbar-right"
					action="${pageContext.request.contextPath}/message/list">
					<input type="text" id="keywords" name="keywords"
						class="form-control" placeholder="搜索..." value="${keywords}">
					<button type="submit" class="btn btn-danger">查询</button>
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="users.jsp">消息列表<span class="sr-only">(current)</span></a>
					</li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/message/list?page=1&pageSize=5&keywords=">全部消息</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="question.jsp">已读消息</a></li>
					<li><a href="question_jstl.jsp">问题列表_jstl</a></li>
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
				<h1 class="sub-header">消息列表</h1>
				<button id="modal" class="btn btn-danger update"
					href="#modal-container-857258" data-toggle="modal">發送信息</button>
				<div class="table-responsive">
					<table class="table table-striped">

						<thead>
							<tr>
								<th>编号</th>
								<th>发送方</th>
								<th>接收方</th>
								<th>内容</th>
								<th>时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${pd.data == null }">
								<jsp:forward page="/message/list?page=1&pageSize=5&keywords="></jsp:forward>
							</c:if>

							<c:forEach items="${pd.data}" var="q">
								<tr>
									<td>${q.messageId}</td>
									<td>${q.senderName}</td>
									<td>${q.receiveName}</td>
									<td>${q.content}</td>
									<td>${q.time}</td>
									<td><button class="layui-btn" onclick="getChange(this)">${q.state==0 ? "未读":"已读"}</button></td>
									<td><button
											class="layui-btn layui-btn-normal layui-btn-radius"
											onclick="updateInfo(this)">修改</button>
										<button class="layui-btn layui-btn-normal layui-btn-radius"
											onclick="getDelete(this)">删除</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="test1" style="text-align: center"></div>
			</div>
		</div>
	</div>

	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="modal fade" id="modal-container-857258" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog " style="width: 800px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">发送信息</h4>
						</div>
						<form class="form-horizontal">
							<input id="senderName" name="senderName" type="hidden"
								value="${sessionScope.userName}">
							<div class="form-group"></div>
							<div class="form-group">
								<label for="receiveName" class="col-sm-2 control-label">接收方:</label>
								<div class="col-sm-8">
									<select class="form-control" name="receiveName"
										id="receiveName">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for=content class="col-sm-2 control-label">内容:</label>
								<div class="col-sm-8">
									<input type="text" required="required" class="form-control"
										name="content" id="content" />
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" id="send" class="btn btn-primary">发送</button>
							</div>
						</form>
						<!-- 表单结束 -->
					</div>

				</div>

			</div>

		</div>
	</div>

	<!-- Modal2 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="commentForm" novalidate="novalidate">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="content">内容:</label> <input type="text"
								class="form-control" id="cMsg" name="cMsg">
						</div>
						<div class="form-group hid">
							<label for="time">日期:</label> <input type="text"
								class="layui-input" id="tMsg" name="tMsg"
								placeholder="yyyy-MM-dd HH:mm:ss">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="change">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/layui.all.js"></script>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script>
		layui
				.use(
						'laypage',
						function() {
							var laypage = layui.laypage;

							var c = 
							${
								pd.total
							}
							;

							var l = 
							${
								pd.pageSize
							}
							;

							var p = 
							${
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
										limits : [ 5, 10, 15 ],
										skip : '#5e7cdf',
										layout : [ 'prev', 'page', 'next',
												'limit', 'skip', 'refresh' ],
										curr : function() {
											var page = p; // 当前页(后台获取到的)
											return page ? page : 1; // 返回当前页码值
										}(),
										jump : function(obj, first) {
											if (!first) {
												location.href = "${pageContext.request.contextPath}/message/list?page="
														+ obj.curr
														+ "&pageSize="
														+ obj.limit
														+ "&keywords="
														+ $("#keywords").val();
											}
										}
									});
						});
	</script>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//日期时间选择器
			laydate.render({
				elem : '#tMsg',
				type : 'datetime'
			});

		});
	</script>
	<script>
	
	
		function getDelete(e) {
			layer.confirm('是否删除?', {
				  btn: ['是的','取消'] //按钮
				}, function(){
					$.ajax({
						url : "${pageContext.request.contextPath}/message/del/"
								+ $(e).parents("tr").find("td").eq(0).text(),
						type : "delete",
						//dataType:"json",
						success : function(data) {
							if (data == "true") {
								layer.alert('删除成功', {
									skin : 'layui-layer-lan',
									closeBtn : 0,
									anim : 6,
									time: 3000,
									end: function () {
						                location.reload();
						            }
								//动画类型
								});
							} else {
								layer.alert('删除失败', {
									skin : 'layui-layer-lan',
									closeBtn : 0,
									anim : 6,
									time: 3000,
									end: function () {
						                location.reload();
						            }
								//动画类型
								});
							}
						}
					});
				});
		};

		$("#modal")
				.click(
						function() {
							$
									.ajax({
										url : "${pageContext.request.contextPath}/message/findName",
										type : "get",
										data : {
											senderName : $("#senderName").val(),

										},
										success : function(data) {
											var info = "";
											$
													.each(
															data,
															function(index, u) {
																//girl 其实就是集合中的每个对象名			
																info += "<option value=\"" + u.userName + "\" >"
																		+ u.userName
																		+ "</option>";
															});
											$("#receiveName").html(info);
										}
									});
						});

		$("#send").click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/message/addMsg",
				type : "post",
				data : {
					senderName : $("#senderName").val(),
					receiveName : $("#receiveName").val(),
					content : $("#content").val(),
				},
				success : function(data) {
					if (data == "true") {
						layer.alert('发送成功', {
							skin : 'layui-layer-lan',
							closeBtn : 0,
							anim : 4,
							time: 3000,
							end: function () {
				                location.reload();
				            }
						//动画类型
						});
					} else {
						layer.alert('发送失败', {
							skin : 'layui-layer-lan',
							closeBtn : 0,
							anim : 4,
							time: 3000,
							end: function () {
				                location.reload();
				            }
						//动画类型
						});
					}
				}
			});
		});

		function getChange(e) {
			var InfoState = $(e).parents("tr").find("td").eq(5).text();
			if (InfoState == "未读") {
				$.ajax({
					url : "${pageContext.request.contextPath}/message/change/"
							+ $(e).parents("tr").find("td").eq(0).text(),
					type : "put",
					contentType : "application/json",
					data : JSON.stringify({
						"state" : "1"
					}),
					success : function(data) {
						window.location.reload();
					}
				});
			} else {
				$.ajax({
					url : "${pageContext.request.contextPath}/message/change/"
							+ $(e).parents("tr").find("td").eq(0).text(),
					type : "put",
					contentType : "application/json",
					data : JSON.stringify({
						"state" : "0"
					}),
					success : function(data) {
						window.location.reload();
					}
				});
			}
		};

		function updateInfo(e) {
			var content = $(e).parents("tr").find("td").eq(3).text();
			var time = $(e).parents("tr").find("td").eq(4).text();
			$("#cMsg").val(content);
			$("#tMsg").val(time);
			$("#myModal").modal("show");
			$("#change").click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/message/update/"
						+ $(e).parents("tr").find("td").eq(0).text(),
				type : "put",
				contentType : "application/json",
				data : JSON.stringify({
					"content" : $("#cMsg").val(),
					"time" : $("#tMsg").val(),
				}),
				success : function(data) {
					$("#myModal").modal("hide");
					if (data == true) {
						layer.alert('修改成功', {
							skin : 'layui-layer-lan',
							closeBtn : 0,
							anim : 4,
							time: 3000,
							end: function () {
				                location.reload();
				            }
						//动画类型
						});
					} else {
						layer.alert('修改失败', {
							skin : 'layui-layer-lan',
							closeBtn : 0,
							anim : 4,
							time: 3000,
							end: function () {
				                location.reload();
				            }
						//动画类型
						});
					}
				}
			});
		  });
		};
	</script>
</body>

</html>