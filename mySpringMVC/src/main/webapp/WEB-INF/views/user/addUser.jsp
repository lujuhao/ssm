<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/taglib.jsp"%>
<html>
<head>
<title>添加用户</title>
</head>
<body>
	<div class="form-group center-block" style="margin-top: 5%">
		<form id="addUserForm" class="form-horizontal" role="form">
			<div class="form-group">
				<label for="name" class="col-sm-offset-2 col-sm-2 control-label">姓名</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-user fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" id="name" name="name" required maxlength="16" placeholder="请输入姓名">
				</div>
			</div>
			
			<div class="form-group">
				<label for="loginName" class="col-sm-offset-2 col-sm-2 control-label">登录名称</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-user-secret fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" id="loginName" name="loginName" required maxlength="16" placeholder="请输入登录名称">
				</div>
			</div>
			
			<div class="form-group">
				<label for="phone" class="col-sm-offset-2 col-sm-2 control-label">手机号码</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-phone fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" id="phone" name="phone" required maxlength="11" placeholder="请输入手机号码">
				</div>
			</div>
			
			<div class="form-group">
				<label for="email" class=" col-sm-offset-2 col-sm-2 control-label">邮箱</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-envelope-o fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱">
				</div>
			</div>
			
			<div class="form-group">
				<label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-key fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="password" class="form-control" id="password" name="password" required maxlength="16" placeholder="请输入密码">
				</div>
			</div>
			
			<div class="form-group">
				<label for="reconfirm_password" class="col-sm-offset-2 col-sm-2 control-label">确认密码</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-check-square-o fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="password" class="form-control" id="reconfirm_password" required maxlength="16" placeholder="请再次输入密码">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-8">
					<button type="submit" class="btn btn-success">确认</button>
					<button type="button" onclick="closeAllLayer()" class="btn btn-info">取消</button>
				</div>
			</div>
		</form>
	</div>
</body>


<script type="text/javascript">

$(function(){
	//自定义验证
 	jQuery.validator.addMethod("phoneRule", function(value, element) {
 		if(!(/^1[3456789]\d{9}$/.test(value))){
 			return false;
 		}else{
 			return true;
 		}
    }, "请输入正确的手机号码");
	
	//绑定添加用户表单
	$("#addUserForm").validate({
	   rules:{
	    	//两次输入相同
	    	reconfirm_password:{
            	equalTo:"#password"
            },
            phone:{
            	phoneRule:true
            },
            email:{
            	email:true
            }
		},
		submitHandler: function(form){
			insertUser();
		},
		errorPlacement: function(error, element) {//错误显示的位置
			layer.tips(error[0].outerText, element, {
				  tips: [2, '#FF0000'], //1-上，2-右,3-下，4-左.还可配置颜色
				  tipsMore: true//不销毁之前的
			});
		}
	});
});

/**
 * 添加用户
 */
function insertUser(){
	layer.load(0);
	$("#addUserForm").ajaxSubmit({
		url:"${ctx}/user/insertUser",
		type:"post",
		success:function(data){
			closeAllLayer();
			parent.layer.msg(data.msg, {icon: data.code, time: 2000});
			parent.initUserTable();
		},
		error:function(){
			layer.closeAll();
			layer.alert("系统异常,请稍候重试");
		}
	});
}

</script>
</html>