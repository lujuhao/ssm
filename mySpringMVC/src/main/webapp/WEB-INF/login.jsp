<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/taglib.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ctxStatic}/bootstrap-switch/js/bootstrap-switch.js"></script>
<link href="${ctxStatic}/bootstrap-switch/css/bootstrap4/bootstrap-switch.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${ctxStatic}/views/login/three.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/views/login/login.js"></script>
<link href="${ctxStatic}/views/login/login.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<div class="loginDiv">
		<form id="loginForm" class="form-horizontal" role="form">
			<div class="input-group margin-bottom-sm col-md-offset-5 col-sm-2">
				<span class="input-group-addon">
					<i class="fa fa-user fa-fw" aria-hidden="true"></i>
				</span> 
				<input type="text" name="loginName" class="form-control" placeholder="请输入用户名" maxlength="16">
			</div>
			
			<div class="input-group col-md-offset-5 col-sm-2">
				<span class="input-group-addon">
					<i class="fa fa-key fa-fw" aria-hidden="true"></i>
				</span> 
				<input type="password" name="password" class="form-control" placeholder="请输入密码" maxlength="16">
			</div>
			
			<div class="input-group col-md-offset-5 col-sm-2">
				<span class="input-group-addon">
					<i class="fa fa-warning" aria-hidden="true"></i>
				</span> 
				<input type="text" name="randomCode" class="form-control" placeholder="请输入验证码" maxlength="4">
			</div>
			
			<div class="input-group col-md-offset-5 col-sm-2">
				<img id="imgcode" alt="点击切换验证码">
			</div>
			
			<div class="input-group col-md-offset-5 col-sm-2">
				<button id="loginButton" type="submit"  class="btn btn-success col-sm-8">登录</button>
				&nbsp;<input type="checkbox" checked="checked" name="rememberMe"/>
			</div>
			
			<div class="input-group col-md-offset-5 col-sm-2 msg">
				
			</div>
		</form>
	</div>
</body>

</html>