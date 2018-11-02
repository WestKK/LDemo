<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>登录界面</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/signin.css"
	rel="stylesheet">
</head>

<body>

	<div class="container">
		<input type="hidden" id="msg" value="${sessionScope.msg}">
		<form class="form-signin" method="post" role="form"
			action="/LoginDemo/loginMsg/login">
			<h2 class="form-signin-heading">请登录</h2>
			<label for="userName" class="sr-only">用户名</label> <input type="text"
				name="userName" class="form-control" placeholder="请输入用户名" required
				autofocus> <label for="userPwd" class="sr-only">密码</label> <input
				type="password" name="userPwd" class="form-control"
				placeholder="请输入密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			<a href="../reg.html"> 还没有账号？ </a>
		</form>


	</div>
	<!-- /container -->

</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script>
	var msg = $("#msg").val();
	if (msg != "") {
		layer.msg(msg);
	}
</script>
</html>